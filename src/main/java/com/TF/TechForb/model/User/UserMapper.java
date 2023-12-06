package com.TF.TechForb.model.User;



import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.repository.UserDocumentTypeRepository;
import com.TF.TechForb.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@NoArgsConstructor
public class UserMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDocumentTypeRepository userDocumentTypeRepository;

    public UserDTO userToUserDto(User user) {
        UserDTO userDTO = new UserDTO(user.getDocumentNumber(),user.getName(), user.getLastname(), user.getUserDocumentType().getIdType());
        return userDTO;
    }
    public UserLoginDTO userToUserLoginDto(User user) {
        UserLoginDTO userLoginDTO = new UserLoginDTO(user.getDocumentNumber(), user.getPassword(), user.getUserDocumentType().getIdType());
        return userLoginDTO;
    }
    public UserCreateDTO userToUserCreateDto(User user) {
        UserCreateDTO userCreateDTO = new UserCreateDTO(user.getDocumentNumber(), user.getPassword(), user.getName(),
                user.getLastname(), user.getUserDocumentType().getIdType());
        return userCreateDTO;
    }


    public User userDtoToUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findByDocumentNumber(userDTO.getDocumentNumber());
        return user.get();
    }
    public User userLoginDtoToUser(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findByDocumentNumber(userLoginDTO.getDocumentNumber());
        return user.get();
    }
    public User userCreateDtoToUser(UserCreateDTO userCreateDTO) {
        Optional<UserDocumentType> userDocumentType = userDocumentTypeRepository.findById(userCreateDTO.getIdUserDocumentType());
        User user = new User(0L,userCreateDTO.getDocumentNumber(), userCreateDTO.getPassword() , userCreateDTO.getName().toLowerCase(),
                userCreateDTO.getLastname().toLowerCase(), userDocumentType.get());
        return user;
    }


    public UserDTO userCreateDtoToUserDto(UserCreateDTO userCreateDTO) {
        UserDTO userDTO = new UserDTO(userCreateDTO.getDocumentNumber(), userCreateDTO.getName().toLowerCase(), userCreateDTO.getLastname().toLowerCase(),
                userCreateDTO.getIdUserDocumentType());
        return userDTO;
    }
}


