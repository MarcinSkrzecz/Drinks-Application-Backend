package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.mapper.RPMapper;
import com.kodilla.drinks_backend.facade.RPFacade;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredientsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class RPController {

    @Autowired
    private RPFacade rpFacade;
    @Autowired
    private RPMapper rpMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/rp/ingredientsDescriptions")
    public List<String> getIngredients() {
        List<String> ingredients = rpFacade.getIngredientsFromRecipePuppyDB();

        return ingredients;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rp/ingredients")
    public List<ProposedIngredientsDto> getAllProposedIngredients() {
        return rpMapper.mapToIProposedIngredientsDtoList(rpFacade.getAllProposedIngredients());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rp/ingredients")
    public void addToProposedIngredients(@RequestParam String ingredientToPropose) {
        rpFacade.addToProposedIngredients(ingredientToPropose);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rp/ingredients/vote")
    public void voteOnIngredient(@RequestParam String ingredientName) {
        rpFacade.voteOnIngredient(ingredientName);
    }

    //ForAdmin
    @RequestMapping(method = RequestMethod.POST, value = "/rp/ingredients/changeStatusAfterRecipeIsAdded")
    public void changeStatusAfterRecipeIsAdded(@RequestParam Long ingredientId) {
        rpFacade.changeStatusAfterRecipeIsAdded(ingredientId);
    }
}