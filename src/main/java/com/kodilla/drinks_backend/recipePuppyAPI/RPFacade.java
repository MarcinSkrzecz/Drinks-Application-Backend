package com.kodilla.drinks_backend.recipePuppyAPI;

import com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.recipePuppyAPI.service.RPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RPFacade {
    @Autowired
    private RPService rpService;

    public List<String> getIngredientsFromRecipePuppyDB() {
        return rpService.getIngredientsFromRecipePuppyDB();
    }

    public List<ProposedIngredients> getAllProposedIngredients() {
        return rpService.getAllProposedIngredients();
    }

    public void addToProposedIngredients(String ingredientToPropose) {
        rpService.addToProposedIngredients(ingredientToPropose);
    }

    public void voteOnIngredient(String ingredientName) {
        rpService.voteOnIngredient(ingredientName);
    }

    public void changeStatusAfterRecipeIsAdded(Long ingredientId) {
        rpService.changeStatusAfterRecipeIsAdded(ingredientId);
    }
}
