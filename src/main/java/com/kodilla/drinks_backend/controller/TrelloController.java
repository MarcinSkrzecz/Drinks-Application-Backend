package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.trelloApiSaveData.TrelloDataToSendDto;
import com.kodilla.drinks_backend.trelloApiSaveData.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.POST, value = "/sendRated")
    public TrelloDataToSendDto sendRatedDataToTrello(@RequestBody TrelloDataToSendDto trelloDataToSendDto) {
        return trelloService.sendRatedDataToTrello(trelloDataToSendDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendProposed")
    public TrelloDataToSendDto sendProposedDataToTrello(@RequestBody TrelloDataToSendDto trelloDataToSendDto) {
        return trelloService.sendProposedDataToTrello(trelloDataToSendDto);
    }
}
