package com.TF.TechForb.repository;

import com.TF.TechForb.model.Transaction.Transaction;
import com.TF.TechForb.model.Transaction.TransactionAccumulatedAmountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.senderAccount.idAccount = :idAccount OR t.receiverAccount.idAccount = :idAccount " +
            "ORDER BY t.date DESC")
    List<Transaction> findByAccount(@Param("idAccount") Long idAccount);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.senderAccount.idAccount = :idAccount OR t.receiverAccount.idAccount = :idAccount " +
            "ORDER BY t.date LIMIT 5")
    List<Transaction> getLatestFiveTransactions(@Param("idAccount") Long idAccount);

    @Query("SELECT t FROM Transaction t INNER JOIN Account a ON t.receiverAccount.idAccount = a.idAccount " +
            "INNER JOIN User u ON a.user.idUser = u.idUser WHERE u.documentNumber = :documentNumber")
    List<Transaction> getReceiverTransactionsByUser(@Param("documentNumber") Integer documentNumber);

    @Query("SELECT t FROM Transaction t INNER JOIN Account a ON t.senderAccount.idAccount = a.idAccount " +
            "INNER JOIN User u ON a.user.idUser = u.idUser WHERE u.documentNumber = :documentNumber")
    List<Transaction> getSenderTransactionsByUser(@Param("documentNumber") Integer documentNumber);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.receiverAccount.idAccount = :idAccount")
    Double getReceiverTransactionsByAccount(@Param("idAccount") Long idAccount);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.senderAccount.idAccount = :idAccount")
    Double getSenderTransactionsByAccount(@Param("idAccount") Long idAccount);


//              Executable queries in mysql

//    @Query(value = "SELECT SUM(t.amount) AS accumulatedAmount, t.date FROM transactions t " +
//            "WHERE t.date >= DATE_ADD(NOW, INTERVAL -7 DAY) " +
//            "AND t.id_account_receiver = :idAccount " +
//            "GROUP BY t.date", nativeQuery = true)
//    List<TransactionAccumulatedAmountDTO> getReceiverTransactionsInTheLastWeekByAccount(@Param("idAccount") Long idAccount);
//    @Query(value = "SELECT SUM(t.amount) AS accumulatedAmount, t.date FROM transactions t " +
//            "WHERE t.date >= DATE_ADD(NOW, INTERVAL -7 DAY) " +
//            "AND t.id_account_sender = :idAccount " +
//            "GROUP BY t.date", nativeQuery = true)
//    List<TransactionAccumulatedAmountDTO> getSenderTransactionsInTheLastWeekByAccount(@Param("idAccount") Long idAccount);

    @Query(value = "SELECT SUM(t.amount) AS accumulatedAmount, t.date " +
            "FROM transactions t " +
            "WHERE t.date >= CURRENT_TIMESTAMP() - INTERVAL '7' DAY " +
            "AND t.id_account_receiver = :idAccount " +
            "GROUP BY t.date", nativeQuery = true)
    List<TransactionAccumulatedAmountDTO> getReceiverTransactionsInTheLastWeekByAccount(@Param("idAccount") Long idAccount);

    @Query(value = "SELECT SUM(t.amount) AS accumulatedAmount, t.date \n" +
            "FROM transactions t " +
            "WHERE t.date >= CURRENT_TIMESTAMP() - INTERVAL '7' DAY " +
            "AND t.id_account_sender = :idAccount " +
            "GROUP BY t.date", nativeQuery = true)
    List<TransactionAccumulatedAmountDTO> getSenderTransactionsInTheLastWeekByAccount(@Param("idAccount") Long idAccount);

    @Query("SELECT t FROM Transaction t WHERE t.status = :status " +
            "AND t.amount = :amount AND t.date = :date " +
            "AND t.senderAccount.idAccount = :idSender AND t.receiverAccount.idAccount = :idReceiver")
    Transaction findByStatusAndAmountAndDateAndSenderAndReceiver(@Param("status") String status,
                                                                 @Param("amount") Double amount,
                                                                 @Param("date") LocalDate date,
                                                                 @Param("idSender") Long idSender,
                                                                 @Param("idReceiver") Long idReceiver);
}
