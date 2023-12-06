package com.TF.TechForb.model.Transaction;

import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.TransactionRepository;
import com.TF.TechForb.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@NoArgsConstructor
public class TransactionMapper {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    public TransactionDTO transactionToTransactionDto(Transaction transaction) {
        Long idSenderAccount = transaction.getSenderAccount().getIdAccount();
        Long idReceiverAccount = transaction.getReceiverAccount().getIdAccount();
        TransactionDTO transactionDTO = new TransactionDTO(transaction.getStatus(),transaction.getAmount(),
                transaction.getDate(),idSenderAccount,idReceiverAccount);
        return transactionDTO;
    }

    public Transaction transactionDtoToTransaction(TransactionDTO transactionDTO) {
        Optional<Account> receiverAccount = accountRepository.findById(transactionDTO.getIdReceiverAccount());
        Optional<Account> senderAccount = accountRepository.findById(transactionDTO.getIdSenderAccount());
        Transaction transaction = new Transaction();
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDate(transactionDTO.getDate());
        transaction.setReceiverAccount(receiverAccount.get());
        transaction.setSenderAccount(senderAccount.get());
        return transaction;
    }

}
