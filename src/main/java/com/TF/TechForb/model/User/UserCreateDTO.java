package com.TF.TechForb.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private Integer documentNumber;
    private String password;
    private String name;
    private String lastname;
    private Long idUserDocumentType;
}
