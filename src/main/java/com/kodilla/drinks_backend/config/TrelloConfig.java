package com.kodilla.drinks_backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TrelloConfig {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.DrinkApplicationTabRating}")
    private String trelloDrinkAppTabRating;

    @Value("${trello.app.DrinkApplicationProposedIngredients}")
    private String trelloDrinkApplicationProposedIngredients;

    @Value("${trello.app.DrinkApplicationContactUsMessage}")
    private String trelloDrinkApplicationContactUsMessage;
}
