package com.kodilla.drinks_backend.trelloApiSaveData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    private String ratedDescriptionFromBuilder(TrelloDataToSendDto trelloDataToSendDto) {
        return "XXX";
    }

    public TrelloDataToSendDto sendRatedDrinkDataToTrello(TrelloDataToSendDto trelloDataToSendDto) {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloDataToSendDto.getDrinkName())
                .queryParam("desc", ratedDescriptionFromBuilder(trelloDataToSendDto))
                .queryParam("pos", "top")
                .queryParam("idList", trelloConfig.getTrelloDrinkAppTabRating())
                .build().encode().toUri();

        return restTemplate.postForObject(url, null, TrelloDataToSendDto.class);
    }

    public TrelloDataToSendDto sendProposedDrinkDataToTrello(TrelloDataToSendDto trelloDataToSendDto) {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloDataToSendDto.getDrinkName())
                .queryParam("desc", trelloDataToSendDto.getDrinkDescription())
                .queryParam("pos", "top")
                .queryParam("idList", trelloConfig.getTrelloDrinkAppTabPropose())
                .build().encode().toUri();

        return restTemplate.postForObject(url, null, TrelloDataToSendDto.class);
    }


}











