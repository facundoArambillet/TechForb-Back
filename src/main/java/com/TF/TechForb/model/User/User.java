package com.TF.TechForb.model.User;

import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "document_number", nullable = false)
    private Integer documentNumber;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;

    @ManyToOne
    @JoinColumn(name="id_type", nullable = false, foreignKey = @ForeignKey(name = "fk_users_user_document_types",
            foreignKeyDefinition = "FOREIGN KEY (id_type) REFERENCES user_document_types(id_type) ON DELETE CASCADE ON UPDATE CASCADE")
    )
    private UserDocumentType userDocumentType;
}
