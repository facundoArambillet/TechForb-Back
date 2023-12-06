package com.TF.TechForb.service;

import com.TF.TechForb.model.Transaction.*;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getAllByAccount(Long idAccount);
    List<Transaction> getLatestTransactions(Long idAccount);
    List<TransactionAccumulatedAmountDTO> getReceiverTransactionsInTheLastWeekByAccount(Long idAccount);
    List<TransactionAccumulatedAmountDTO> getSenderTransactionsInTheLastWeekByAccount(Long idAccount);
    List<TransactionResponseChartDTO> getTransactionWeek(Long idAccount);
    TransactionDTO makeTransaction(TransactionDTO transactionDTO);
    TransactionDepositWithdrawalDTO makeDeposit(TransactionDepositWithdrawalDTO transaction);
    TransactionDepositWithdrawalDTO makeWithdrawal(TransactionDepositWithdrawalDTO transaction);
    Double getIncomeBalanceByAccount(Long idAccount);
    Double getOutflowBalanceByAccount(Long idAccount);
}
