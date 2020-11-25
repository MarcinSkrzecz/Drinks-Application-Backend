package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.facade.ProposedIngredientsFacade;
import com.kodilla.drinks_backend.mapper.ProposedIngredientsMapper;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredientsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProposedIngredientsController {

    @Autowired
    private ProposedIngredientsFacade proposedIngredientsFacade;
    @Autowired
    private ProposedIngredientsMapper proposedIngredientsMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/proposedIngredients")
    public List<ProposedIngredientsDto> getAllProposedIngredients() {
        return proposedIngredientsMapper.mapToProposedIngredientsDtoList(proposedIngredientsFacade.getAllIngredients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/proposedIngredients/{ingredientId}")
    public ProposedIngredientsDto getProposedIngredient(@RequestParam Long ingredientId) {
        return proposedIngredientsMapper.mapToProposedIngredientsDto(proposedIngredientsFacade.getIngredient(ingredientId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/proposedIngredients")
    public void createIngredient(@RequestParam String proposedIngredient) {
        proposedIngredientsFacade.createIngredient(proposedIngredientsMapper.mapToProposedIngredients_Create(proposedIngredient));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/proposedIngredients/{ingredientId}")
    public void deleteIngredient(@RequestParam Long ingredientId) {
        proposedIngredientsFacade.deleteIngredient(ingredientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/proposedIngredients/vote/{ingredientId}")
    public void voteOnIngredient(@RequestParam Long ingredientId) {
        proposedIngredientsFacade.voteOnIngredient(ingredientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/proposedIngredients/changeStatusToCompleted/{ingredientId}")
    public void changeStatusToCompleted(@RequestParam Long ingredientId) {
        proposedIngredientsFacade.changeStatusToCompleted(ingredientId);
    }
}
