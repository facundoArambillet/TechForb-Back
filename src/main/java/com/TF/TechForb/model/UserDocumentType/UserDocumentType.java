package com.TF.TechForb.model.UserDocumentType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_document_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Long idType;
    @Column(nullable = false)
    private String type;
}
