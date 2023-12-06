package com.TF.TechForb.service;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.Account.AccountDTO;
import com.TF.TechForb.model.Account.AccountDetailDTO;
import com.TF.TechForb.model.Account.AccountMapper;
import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.model.User.User;
import com.TF.TechForb.model.User.UserDTO;
import com.TF.TechForb.model.User.UserMapper;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.CardRepository;
import com.TF.TechForb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountMapper accountMapper;

    public AccountDetailDTO getByUser(Integer documentNumber) {
        try {
            Optional<User> userFounded = userRepository.findByDocumentNumber(documentNumber);
            if(userFounded.isPresent()) {
                Optional<Account> accountFounded = accountRepository.findByUser(userFounded.get().getIdUser());
                if (accountFounded.isPresent()) {
                    AccountDetailDTO accountDetailDTO = accountMapper.accountToAccountDetailDto(accountFounded.get());
                    return accountDetailDTO;
                } else {
                    throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
                }
            } else {
                throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in create account", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public AccountDTO createAccount(Account account) {
        try {
            Optional<Account> accountFounded = accountRepository.findByUser(account.getUser().getIdUser());
            if(accountFounded.isPresent()) {
                throw new TechForbException("That user already have a account", null, HttpStatus.BAD_REQUEST);
            } else {
                AccountDTO accountDTO = accountMapper.accountToAccountDto(account);
                Card card = new Card();
                String cardHolder = account.getUser().getName() + " " + account.getUser().getLastname();
                String cardNumber = card.generateCardNumber();
                card.setCardholder(cardHolder);
                card.setCardNumber(cardNumber);
                card.setBalance(0D);
                card.setValidUntil(LocalDate.now().plusYears(5));
                card.setAccount(account);
                accountRepository.save(account);
                cardService.createCard(card);

                return accountDTO;
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in create account", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
