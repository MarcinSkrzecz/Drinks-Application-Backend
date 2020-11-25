package com.kodilla.drinks_backend.client;

import com.kodilla.drinks_backend.config.TrelloConfig;
import com.kodilla.drinks_backend.domain.trello.TrelloMessageDto;
import com.kodilla.drinks_backend.mapper.TrelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class TrelloClient {

    @Autowired
    private TrelloConfig trelloConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TrelloMapper trelloMapper;

    public TrelloMessageDto sendRatedDrinkDataToTrello(Long drinkId) {
        TrelloMessageDto trelloMessageDto = trelloMapper.mapDrinkDataToSend(drinkId);

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloMessageDto.getTitle())
                .queryParam("desc", trelloMessageDto.getContent())
                .queryParam("pos", "top")
                .queryParam("idList", trelloConfig.getTrelloDrinkAppTabRating())
                .build().encode().toUri();

        return restTemplate.postForObject(url, null, TrelloMessageDto.class);
    }

    public TrelloMessageDto sendProposedIngredientDataToTrello(Long proposedIngredientId) {
        TrelloMessageDto trelloMessageDto = trelloMapper.mapProposedIngredientDataToSend(proposedIngredientId);

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloMessageDto.getTitle())
                .queryParam("desc", trelloMessageDto.getContent())
                .queryParam("pos", "top")
                .queryParam("idList", trelloConfig.getTrelloDrinkApplicationProposedIngredients())
                .build().encode().toUri();

        return restTemplate.postForObject(url, null, TrelloMessageDto.class);
    }

    public TrelloMessageDto sendContactUsMessage(TrelloMessageDto trelloMessageDto) {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloMessageDto.getTitle())
                .queryParam("desc", trelloMessageDto.getContent())
                .queryParam("pos", "top")
                .queryParam("idList", trelloConfig.getTrelloDrinkApplicationContactUsMessage())
                .build().encode().toUri();

        return restTemplate.postForObject(url, null, TrelloMessageDto.class);
    }
}