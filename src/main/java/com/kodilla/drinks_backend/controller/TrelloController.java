package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.trello.TrelloMessageDto;
import com.kodilla.drinks_backend.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.POST, value = "/sendRated")
    public TrelloMessageDto sendRatedDataToTrello(@RequestParam Long drinkId) {
        return trelloFacade.sendRatedDataToTrello(drinkId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendProposed")
    public TrelloMessageDto sendProposedDataToTrello(@RequestParam Long proposedIngredientId) {
        return trelloFacade.sendProposedIngredientDataToTrello(proposedIngredientId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    public TrelloMessageDto sendContactUsMessageToTrello(@RequestBody TrelloMessageDto trelloMessageDto) {
        return trelloFacade.sendContactUsMessageToTrello(trelloMessageDto);
    }
}
