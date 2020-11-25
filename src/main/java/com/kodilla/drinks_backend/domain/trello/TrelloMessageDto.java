package com.kodilla.drinks_backend.domain.trello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloMessageDto {
    private String title;
    private String content;
}
