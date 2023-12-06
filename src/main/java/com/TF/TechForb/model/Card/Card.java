package com.TF.TechForb.model.Card;

import com.TF.TechForb.model.Account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Long idCard;
    @Column(nullable = false, length = 16)
    private String cardNumber;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private String cardholder;
    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;

    @OneToOne
    @JoinColumn(name="id_account", nullable = false, foreignKey = @ForeignKey(name = "fk_cards_accounts",
            foreignKeyDefinition = "FOREIGN KEY (id_account) REFERENCES accounts(id_account) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private Account account;

    public String generateCardNumber() {
        Random random = new Random();
        int upperBound = 10;
        String cardNumber = "";
        for(int i = 0; i < 12; i++) {
            Integer randomNumber = random.nextInt(upperBound);
            cardNumber += randomNumber.toString() ;
        }
        cardNumber += "1234";

        return cardNumber;
    }
}
