package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.TrelloMessageDto;
import com.kodilla.drinks_backend.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.POST, value = "/sendRated")
    public TrelloMessageDto sendRatedDataToTrello(@RequestParam Long drinkId) {
        return trelloService.sendRatedDataToTrello(drinkId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendProposed")
    public TrelloMessageDto sendProposedDataToTrello(@RequestParam Long drinkId) {
        return trelloService.sendProposedIngredientDataToTrello(drinkId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    public TrelloMessageDto sendContactUsMessageToTrello(@RequestBody TrelloMessageDto trelloMessageDto) {
        return trelloService.sendContactUsMessageToTrello(trelloMessageDto);
    }
}
