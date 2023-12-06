package com.TF.TechForb.service;

import com.TF.TechForb.model.User.UserCreateDTO;
import com.TF.TechForb.model.User.UserDTO;
import com.TF.TechForb.model.User.UserLoginDTO;
import com.TF.TechForb.security.Token;

public interface UserService {
    UserDTO getById(Long id);
    UserDTO getByDocumentNumber(Integer documentNumber);
    UserDTO getByAccount(Long idAccount);
    UserDTO register(UserCreateDTO userCreateDTO);
    Token login(UserLoginDTO userLoginDTO);
    UserDTO deleteUser(Long id);
}
