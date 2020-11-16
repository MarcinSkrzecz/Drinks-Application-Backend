package com.kodilla.drinks_backend.trelloApiSaveData;

import com.kodilla.drinks_backend.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrelloService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    public TrelloDataToSendDto sendRatedDataToTrello(final TrelloDataToSendDto trelloDataToSendDto) {
        TrelloDataToSendDto cardToSend = trelloClient.sendRatedDrinkDataToTrello(trelloDataToSendDto);
        return cardToSend;
    }

    public TrelloDataToSendDto sendProposedDataToTrello(final TrelloDataToSendDto trelloDataToSendDto) {
        TrelloDataToSendDto cardToSend = trelloClient.sendProposedDrinkDataToTrello(trelloDataToSendDto);
        return cardToSend;
    }
}
