package com.TF.TechForb.model.Transaction;

import com.TF.TechForb.model.Account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long idTransaction;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Double amount;
//    @Column(name = "transaction_type",nullable = false)
//    private Boolean transactionType;
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="id_account_sender", nullable = false, foreignKey = @ForeignKey(name = "fk_transactions_sender_accounts",
            foreignKeyDefinition = "FOREIGN KEY (id_account_sender) REFERENCES accounts(id_account) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private Account senderAccount;
    @ManyToOne
    @JoinColumn(name="id_account_receiver", nullable = false, foreignKey = @ForeignKey(name = "fk_transactions_receiver_accounts",
            foreignKeyDefinition = "FOREIGN KEY (id_account_receiver) REFERENCES accounts(id_account) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private Account receiverAccount;

}
