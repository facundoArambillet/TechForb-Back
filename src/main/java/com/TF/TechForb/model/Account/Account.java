package com.TF.TechForb.model.Account;

import com.TF.TechForb.model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long idAccount;
//    @Column(nullable = false)
//    private Double balance;

    @OneToOne
    @JoinColumn(name="id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_accounts_user",
            foreignKeyDefinition = "FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private User user;
}
