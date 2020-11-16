package com.kodilla.drinks_backend.domain.drink;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DrinkDto_Update {
    private Long id;
    private String username;
    private String drinkName;
    private String recipe;
    private String ingredients;
}
