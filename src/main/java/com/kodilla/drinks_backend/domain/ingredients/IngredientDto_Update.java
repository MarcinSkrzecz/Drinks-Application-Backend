package com.kodilla.drinks_backend.domain.ingredients;

import lombok.Getter;

@Getter
public class IngredientDto_Update {
    private Long id;
    private String description;

    public IngredientDto_Update(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
