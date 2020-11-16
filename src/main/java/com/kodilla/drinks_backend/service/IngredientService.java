package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.domain.ingredients.Ingredient;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientService {

    @Autowired
    private IngredientDao ingredientDao;
    @Autowired
    private IngredientServiceBackground ingredientServiceBackground;

    public List<Ingredient> getAllIngredients() {
        return ingredientDao.findAll();
    }

    public Ingredient getIngredient(final Long id) {
        if (ingredientDao.findById(id).isPresent()) {
            return ingredientDao.findById(id).get();
        } else throw new IllegalArgumentException("Ingredient not found");
    }


    public void regenerateIngredients_DrinkCreate(String ingredients)  {
        ingredientServiceBackground.regenerateIngredients_DrinkCreate(ingredients);
    }

    public void regenerateIngredients_DrinkUpdate(String newIngredients, String oldIngredients) {
        ingredientServiceBackground.regenerateIngredients_DrinkUpdate(newIngredients,oldIngredients);
    }

    public void regenerateIngredients_DrinkDelete(String ingredients) {
        ingredientServiceBackground.regenerateIngredients_DrinkDelete(ingredients);
    }

    public void deleteIngredient(long id) {
        ingredientDao.deleteById(id);
    }
}
