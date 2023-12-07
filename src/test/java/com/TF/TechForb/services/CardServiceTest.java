package com.TF.TechForb.services;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.model.MenuItem.MenuItem;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.CardRepository;
import com.TF.TechForb.repository.UserRepository;
import com.TF.TechForb.service.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @Test
    public void givenCards_whenGetAll_thenListShouldNotBeNull() {
        List<Card> cards = new ArrayList<>();
        when(cardRepository.findAll()).thenReturn(cards);

        assertEquals(cardService.getAll(), cardRepository.findAll());
    }

    @Test
    public void givenAlreadyExistCard_whenCreate_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("That Number Card already exist", null, HttpStatus.BAD_REQUEST);
        Card card = new Card();
        card.setCardNumber("123456789");

        when(cardRepository.findByCardNumber(card.getCardNumber())).thenReturn(Optional.of(card));

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                cardService.createCard(card));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());

    }
}
