package com.kodilla.drinks_backend.facade;

import com.kodilla.drinks_backend.domain.trello.TrelloMessageDto;
import com.kodilla.drinks_backend.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrelloFacade {
    @Autowired
    private TrelloService trelloService;

    public TrelloMessageDto sendRatedDataToTrello(final Long drinkId) {
        return trelloService.sendRatedDataToTrello(drinkId);
    }
    public TrelloMessageDto sendProposedIngredientDataToTrello(final Long proposedIngredientId) {
        return trelloService.sendProposedIngredientDataToTrello(proposedIngredientId);
    }
    public TrelloMessageDto sendContactUsMessageToTrello(final TrelloMessageDto trelloMessageDto) {
        return trelloService.sendContactUsMessageToTrello(trelloMessageDto);
    }
}