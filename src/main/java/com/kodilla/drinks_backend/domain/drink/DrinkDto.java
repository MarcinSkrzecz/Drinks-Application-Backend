package com.kodilla.drinks_backend.domain.drink;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DrinkDto {
    private Long id;
    private String username;
    private String drinkName;
    private String recipe;
    private String ingredients;
    private LocalDate creationDate;

    public DrinkDto(Long id, String username, String drinkName, String recipe, String ingredients, LocalDate creationDate) {
        this.id = id;
        this.username = username;
        this.drinkName = drinkName;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.creationDate = creationDate;
    }
}
