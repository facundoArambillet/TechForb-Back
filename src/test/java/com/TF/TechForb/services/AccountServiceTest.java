package com.TF.TechForb.services;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.User.User;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.UserRepository;
import com.TF.TechForb.service.AccountService;
import com.TF.TechForb.service.AccountServiceImpl;
import com.TF.TechForb.service.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CardServiceImpl cardService;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void givenInvalidUser_whenGetByUser_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
        User user = new User();
        user.setDocumentNumber(12345678);

        when(userRepository.findByDocumentNumber(user.getDocumentNumber())).thenReturn(Optional.empty());
        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                accountService.getByUser(user.getDocumentNumber()));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());
    }
    @Test
    public void givenAlreadyExistingUser_whenCreate_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("That user already have a account", null,
                HttpStatus.BAD_REQUEST);
        User user = new User();
        Account account = new Account();
        user.setIdUser(1L);
        account.setUser(user);

        when(accountRepository.findByUser(account.getUser().getIdUser())).thenReturn(Optional.of(account));
        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                accountService.createAccount(account));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());
    }
}
