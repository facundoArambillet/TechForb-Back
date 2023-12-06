package com.TF.TechForb.model.Card;

import com.TF.TechForb.repository.CardRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@NoArgsConstructor
public class CardMapper {
    @Autowired
    private CardRepository cardRepository;

    public CardDepositDTO cardToCardDepositDto(Card card) {
        CardDepositDTO cardDepositDTO = new CardDepositDTO(card.getCardNumber(),card.getBalance(),
                card.getCardholder(),card.getValidUntil());

        return cardDepositDTO;
    }
    public CardDTO cardToCardDto(Card card) {
        CardDTO cardDTO = new CardDTO(card.getCardNumber(),card.getBalance(),card.getCardholder(),card.getValidUntil());

        return cardDTO;
    }


    public Card cardDepositDtoToCard(CardDepositDTO cardDepositDTO) {
        Optional<Card> card = cardRepository.findByCardNumber(cardDepositDTO.getCardNumber());

        return card.get();
    }
    public Card cardDtoToCard(CardDTO cardDTO) {
        Optional<Card> card = cardRepository.findByCardNumber(cardDTO.getCardNumber());

        return card.get();
    }
}
