package com.kodilla.drinks_backend.facade;

import com.kodilla.drinks_backend.domain.ingredients.Ingredient;
import com.kodilla.drinks_backend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IngredientFacade {
    @Autowired
    private IngredientService ingredientService;

    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
    public Ingredient getIngredient(final Long id) {
        return ingredientService.getIngredient(id);
    }
}