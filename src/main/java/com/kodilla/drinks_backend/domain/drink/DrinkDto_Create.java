package com.kodilla.drinks_backend.domain.drink;

import lombok.Getter;

@Getter
public class DrinkDto_Create {
    private String username;
    private String drinkName;
    private String recipe;
    private String ingredients;

    public DrinkDto_Create(String username, String drinkName, String recipe, String ingredients) {
        this.username = username;
        this.drinkName = drinkName;
        this.recipe = recipe;
        this.ingredients = ingredients;
    }
}
