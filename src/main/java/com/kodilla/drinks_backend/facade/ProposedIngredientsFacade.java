package com.kodilla.drinks_backend.facade;

import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.service.ProposedIngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProposedIngredientsFacade {
    @Autowired
    private ProposedIngredientsService proposedIngredientsService;

    public List<ProposedIngredients> getAllIngredients() {
        return proposedIngredientsService.getAllIngredients();
    }
    public ProposedIngredients getIngredient(Long id) {
        return proposedIngredientsService.getIngredient(id);
    }
    public void createIngredient(final ProposedIngredients proposedIngredients) {
        proposedIngredientsService.createIngredient(proposedIngredients);
    }
    public void deleteIngredient(final Long id) {
        proposedIngredientsService.deleteIngredient(id);
    }
    public void voteOnIngredient(Long id) {
        proposedIngredientsService.voteOnIngredient(id);
    }
    public void changeStatusToCompleted(final Long id) {
        proposedIngredientsService.changeStatusToCompleted(id);
    }
}