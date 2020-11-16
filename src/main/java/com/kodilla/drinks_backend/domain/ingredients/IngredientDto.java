package com.kodilla.drinks_backend.domain.ingredients;

import lombok.Getter;

@Getter
public class IngredientDto {
    private Long id;
    private String description;
    private int howManyTimesUsed;

    public IngredientDto(Long id, String description, int howManyTimesUsed) {
        this.id = id;
        this.description = description;
        this.howManyTimesUsed = howManyTimesUsed;
    }
}
