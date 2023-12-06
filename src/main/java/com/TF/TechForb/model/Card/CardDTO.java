package com.TF.TechForb.model.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String cardNumber;
    private Double balance;
    private String cardholder;
    private LocalDate validUntil;
}
