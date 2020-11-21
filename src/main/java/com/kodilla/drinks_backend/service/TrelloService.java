package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.email_services.EmailSender;
import com.kodilla.drinks_backend.trelloApiSaveData.TrelloClient;
import com.kodilla.drinks_backend.domain.TrelloMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrelloService {

    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private EmailSender emailSender;

    public TrelloMessageDto sendRatedDataToTrello(final Long drinkId) {
        TrelloMessageDto cardToSend = trelloClient.sendRatedDrinkDataToTrello(drinkId);
        emailSender.sendEmailAboutHighlyRatedDrink(drinkId);
        return cardToSend;
    }

    public TrelloMessageDto sendProposedIngredientDataToTrello(final Long proposedIngredientId) {
        TrelloMessageDto cardToSend = trelloClient.sendProposedIngredientDataToTrello(proposedIngredientId);
        emailSender.sendEmailAboutIngredientProposedToCreateDrink(proposedIngredientId);
        return cardToSend;
    }

    public TrelloMessageDto sendContactUsMessageToTrello(final TrelloMessageDto trelloMessageDto) {
        TrelloMessageDto cardToSend = trelloClient.sendContactUsMessage(trelloMessageDto);
        emailSender.sendEmailAboutSomeoneContactingUs(trelloMessageDto.getTitle(), trelloMessageDto.getContent());
        return cardToSend;
    }
}
