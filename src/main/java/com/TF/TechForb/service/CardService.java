package com.TF.TechForb.service;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.model.Card.CardDTO;
import com.TF.TechForb.model.Card.CardDepositDTO;
import com.TF.TechForb.model.Card.CardMapper;
import com.TF.TechForb.model.User.User;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.CardRepository;
import com.TF.TechForb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardMapper cardMapper;

    public List<Card> getAll() {
        try {
            return cardRepository.findAll();
        } catch (Exception e) {
            throw new TechForbException("Error in get all", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public CardDTO getById(Long id) {
        try {
            Optional<Card> cardFounded = cardRepository.findById(id);
            if (cardFounded.isPresent()) {
                CardDTO cardDTO = cardMapper.cardToCardDto(cardFounded.get());

                return cardDTO;
            } else {
                throw new TechForbException("Card not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by id", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public CardDTO getByIdAccount(Long idAccount) {
        try {
            Optional<Account> accountFounded = accountRepository.findById(idAccount);
            if(accountFounded.isPresent()) {
                Optional<Card> cardFounded = cardRepository.findByAccount(idAccount);
                if(cardFounded.isPresent()) {
                    CardDTO cardDTO = cardMapper.cardToCardDto(cardFounded.get());

                    return cardDTO;
                } else {
                    throw new TechForbException("Card not found", null, HttpStatus.NOT_FOUND);
                }
            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by id", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public CardDTO getByUserDocument(Integer userDocument) {
        try {
            Optional<User> userFounded = userRepository.findByDocumentNumber(userDocument);
            if(userFounded.isPresent()) {
                Optional<Card> cardFounded = cardRepository.findByUserDocument(userDocument);
                if(cardFounded.isPresent()) {
                    CardDTO cardDTO = cardMapper.cardToCardDto(cardFounded.get());

                    return cardDTO;
                } else {
                    throw new TechForbException("Card not found", null, HttpStatus.NOT_FOUND);
                }
            } else {
                throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by id", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Double getBalance(Long id) {
        try {
            Optional<Card> cardFounded = cardRepository.findById(id);
            if(cardFounded.isPresent()) {
                return cardFounded.get().getBalance();
            } else {
                throw new TechForbException("Card not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in create card", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Card createCard(Card card) {
        try {
            Optional<Card> cardFounded = cardRepository.findByCardNumber(card.getCardNumber());
            if(cardFounded.isPresent()) {
                throw new TechForbException("That Number Card already exist", null, HttpStatus.BAD_REQUEST);
            } else {

                return cardRepository.save(card);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in create card", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Card deleteCard(Long id) {
        try {
            Optional<Card> cardFounded = cardRepository.findById(id);
            if (cardFounded.isPresent()) {
                cardRepository.delete(cardFounded.get());
                return cardFounded.get();
            } else {
                throw new TechForbException("Card not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in delete card", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
