package com.TF.TechForb.controller;

import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.model.Card.CardDTO;
import com.TF.TechForb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAll() {
        return cardService.getAll();
    }
    @GetMapping("/{idCard}")
    public CardDTO getById(@PathVariable("idCard") Long idCard) {
        return cardService.getById(idCard);
    }
    @GetMapping("/byAccount/{idAccount}")
    public CardDTO getByIdAccount(@PathVariable("idAccount") Long idAccount) {
        return cardService.getByIdAccount(idAccount);
    }
    @GetMapping("byUser/{userDocument}")
    public CardDTO getByUserDocument(@PathVariable("userDocument") Integer userDocument) {
        return cardService.getByUserDocument(userDocument);
    }
    @GetMapping("/balance/{idCard}")
    public Double getBalance(@PathVariable("idCard") Long idCard) {
        return cardService.getBalance(idCard);
    }
    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }
//    @PostMapping("/deposit")
//    public CardDepositDTO makeDeposit(@RequestBody CardDepositDTO cardDepositDTO) {
//        return cardService.makeDeposit(cardDepositDTO);
//    }
//    @PostMapping
//    public CardDepositDTO makeWithdrawal(@RequestBody CardDepositDTO cardDepositDTO) {
//        return cardService.makeWithdrawal(cardDepositDTO);
//    }
    @DeleteMapping("/{idCard}")
    public Card deleteCard(@PathVariable("idCard") Long idCard) {
        return cardService.deleteCard(idCard);
    }
}
