package com.TF.TechForb.model.User;

import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private Integer documentNumber;
    private String password;
    private Long idUserDocumentType;
}
