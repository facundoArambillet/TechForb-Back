package com.TF.TechForb.model.Transaction;

import java.time.LocalDate;


public interface TransactionAccumulatedAmountDTO {
    Double getAccumulatedAmount();
    LocalDate getDate();
}
