package com.TF.TechForb.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseChartDTO {
    LocalDate date;
    Double receiverAmount;
    Double senderAmount;
}
