package com.TF.TechForb.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String status;
    private Double amount;
    private LocalDate date;
    private Long idSenderAccount;
    private Long idReceiverAccount;
}
