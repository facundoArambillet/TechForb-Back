package com.TF.TechForb.model.Transaction;

import com.TF.TechForb.model.Account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDepositWithdrawalDTO {
    private Double amount;
    private Long idAccount;
}
