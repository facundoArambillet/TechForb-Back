package com.TF.TechForb.services;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.User.User;
import com.TF.TechForb.model.User.UserCreateDTO;
import com.TF.TechForb.model.User.UserLoginDTO;
import com.TF.TechForb.model.User.UserMapper;
import com.TF.TechForb.repository.UserDocumentTypeRepository;
import com.TF.TechForb.repository.UserRepository;
import com.TF.TechForb.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private User user;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void givenInvalidUserId_whenGetById_thenReturnNotFound() {
        TechForbException exceptionExpected = new TechForbException("User not found", null, HttpStatus.NOT_FOUND);

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () -> userService.getById(1L));

        assertEquals(exceptionExpected.getHttpStatus(),exceptionCaptured.getHttpStatus());
    }
    @Test
    public void givenAlreadyExistUser_whenRegister_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("This user already exist ", null, HttpStatus.BAD_REQUEST);
        UserCreateDTO userCreateDTO = new UserCreateDTO(12345678,"password","name","lastname",1L);
        when(userRepository.findByDocumentNumber(userCreateDTO.getDocumentNumber())).thenReturn(Optional.of(user));

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                userService.register(userCreateDTO));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());

    }
    @Test
    public void givenInvalidPassword_whenLogin_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("Password incorrect", null, HttpStatus.BAD_REQUEST);
        UserLoginDTO userLoginDTO = new UserLoginDTO(12345678,"password invalid",1L);
        user.setPassword(passwordEncoder.encode("password"));
        userLoginDTO.setPassword(passwordEncoder.encode("password invalid"));

        when(userRepository.findByDocumentNumber(userLoginDTO.getDocumentNumber())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(user.getPassword(), userLoginDTO.getPassword())).thenReturn(false);

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                userService.login(userLoginDTO));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());
    }
}
