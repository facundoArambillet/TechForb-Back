package com.TF.TechForb.service;

import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.model.Card.CardDTO;

import java.util.List;

public interface CardService {
    List<Card> getAll();
    CardDTO getById(Long id);
    CardDTO getByIdAccount(Long idAccount);
    CardDTO getByUserDocument(Integer userDocument);
    Double getBalance(Long id);
    Card createCard(Card card);
    Card deleteCard(Long id);

}
