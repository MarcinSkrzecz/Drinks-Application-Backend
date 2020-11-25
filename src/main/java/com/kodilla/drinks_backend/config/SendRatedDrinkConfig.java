package com.kodilla.drinks_backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SendRatedDrinkConfig {
    @Value("${trello.app.SendRatedRating}")
    private Double ratingToSendData;
    @Value("${trello.app.SendRatedComment}")
    private Integer commentsToSendData;
}
