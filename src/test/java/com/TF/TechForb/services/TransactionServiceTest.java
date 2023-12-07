package com.TF.TechForb.services;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.model.Transaction.TransactionDTO;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.CardRepository;
import com.TF.TechForb.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private Account account;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void givenInvalidSenderAccount_whenMakeTransaction_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("Sender Account not found", null, HttpStatus.NOT_FOUND);
        TransactionDTO transactionDTO = new TransactionDTO("Status",1000D, LocalDate.now(),
                1L,2L);
        when(accountRepository.findById(transactionDTO.getIdSenderAccount())).thenReturn(Optional.empty());

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                transactionService.makeTransaction(transactionDTO));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());
    }
    @Test
    public void givenInvalidReceiverAccount_whenMakeTransaction_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("Receiver Account not found", null, HttpStatus.NOT_FOUND);
        TransactionDTO transactionDTO = new TransactionDTO("Status",1000D, LocalDate.now(),
                1L,2L);
        when(accountRepository.findById(transactionDTO.getIdSenderAccount())).thenReturn(Optional.of(account));
        when(accountRepository.findById(transactionDTO.getIdReceiverAccount())).thenReturn(Optional.empty());

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                transactionService.makeTransaction(transactionDTO));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());
    }

    @Test
    public void givenInsufficientBalance_whenMakeTransaction_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("Insufficient Founds", null, HttpStatus.BAD_REQUEST);
        TransactionDTO transactionDTO = new TransactionDTO("Status",1000D, LocalDate.now(),
                1L,2L);
        Account accountSender = new Account();
        Account accountReceiver = new Account();
        accountSender.setIdAccount(1L);
        accountReceiver.setIdAccount(2L);
        Card senderCard = new Card();
        senderCard.setBalance(500D);

        when(accountRepository.findById(transactionDTO.getIdSenderAccount())).thenReturn(Optional.of(accountSender));
        when(accountRepository.findById(transactionDTO.getIdReceiverAccount())).thenReturn(Optional.of(accountReceiver));
        when(cardRepository.findByAccount(transactionDTO.getIdSenderAccount())).thenReturn(Optional.of(senderCard));

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                transactionService.makeTransaction(transactionDTO));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());
    }
}
