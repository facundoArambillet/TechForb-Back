package com.TF.TechForb.controller;

import com.TF.TechForb.model.Transaction.*;
import com.TF.TechForb.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/{idAccount}")
    public List<TransactionDTO> getAllByAccount(@PathVariable("idAccount") Long idAccount) {
        return transactionService.getAllByAccount(idAccount);
    }
//    @GetMapping("/receiver-transaction/{idAccount}")
//    public List<TransactionDTO> getReceiverTransactionsByAccount(@PathVariable("idAccount") Long idAccount) {
//        return transactionService.getReceiverTransactionsByAccount(idAccount);
//    }
//    @GetMapping("/sender-transaction/{idAccount}")
//    public List<TransactionDTO> getSenderTransactionsByAccount(@PathVariable("idAccount") Long idAccount) {
//        return transactionService.getSenderTransactionsByAccount(idAccount);
//    }
    @GetMapping("/latest/{idAccount}")
    public List<Transaction> getLatestTransactions(@PathVariable("idAccount") Long idAccount) {
        return transactionService.getLatestTransactions(idAccount);
    }
    @GetMapping("/balance-income/{idAccount}")
    public Double getIncomeBalanceByAccount(@PathVariable("idAccount") Long idAccount) {
        return transactionService.getIncomeBalanceByAccount(idAccount);
    }
    @GetMapping("/balance-outflow/{idAccount}")
    public Double getOutflowBalanceByAccount(@PathVariable("idAccount") Long idAccount) {
        return transactionService.getOutflowBalanceByAccount(idAccount);
    }
    @GetMapping("/last-week/receiver/{idAccount}")
    public List<TransactionAccumulatedAmountDTO> getReceiverTransactionsInTheLastWeekByAccount(@Param("idAccount") Long idAccount) {
        return transactionService.getReceiverTransactionsInTheLastWeekByAccount(idAccount);
    }
    @GetMapping("/last-week/sender/{idAccount}")
    public List<TransactionAccumulatedAmountDTO> getSenderTransactionsInTheLastWeekByAccount(@Param("idAccount") Long idAccount) {
        return transactionService.getSenderTransactionsInTheLastWeekByAccount(idAccount);
    }
    @GetMapping("/week/{idAccount}")
    public List<TransactionResponseChartDTO> getTransactionWeek(@PathVariable("idAccount") Long idAccount) {
        return transactionService.getTransactionWeek(idAccount);
    }
    @PostMapping
    public TransactionDTO makeTransaction(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.makeTransaction(transactionDTO);
    }
    @PostMapping("/deposit")
    public TransactionDepositWithdrawalDTO makeDeposit(@RequestBody TransactionDepositWithdrawalDTO transaction) {
        return transactionService.makeDeposit(transaction);
    }
    @PostMapping("/withdrawal")
    public TransactionDepositWithdrawalDTO makeWithdrawal(@RequestBody TransactionDepositWithdrawalDTO transaction) {
        return transactionService.makeWithdrawal(transaction);
    }
}
