package com.kodilla.drinks_backend.trelloApiSaveData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloDataToSendDto {

    private String drinkName;
    private String drinkDescription;
}
