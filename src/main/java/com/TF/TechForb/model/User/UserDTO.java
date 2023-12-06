package com.TF.TechForb.model.User;

import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer documentNumber;
    private String name;
    private String lastname;
    private Long idUserDocumentType;
}
