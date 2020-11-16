package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.mapper.RPMapper;
import com.kodilla.drinks_backend.recipePuppyAPI.RPFacade;
import com.kodilla.drinks_backend.recipePuppyAPI.domain.ProposedIngredientsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/recipePuppy/")
public class RPController {

    @Autowired
    private RPFacade rpFacade;
    @Autowired
    private RPMapper rpMapper;

    //ForTests now
    @RequestMapping(method = RequestMethod.GET, value = "getIngredients")
    public void getIngredients() {
        List<String> ingredients = rpFacade.getIngredientsFromRecipePuppyDB();

        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println(ingredients.get(i));
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllProposedIngredients")
    public List<ProposedIngredientsDto> getAllProposedIngredients() {
        return rpMapper.mapToIProposedIngredientsDtoList(rpFacade.getAllProposedIngredients());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addToProposedIngredients")
    public void addToProposedIngredients(@RequestParam String ingredientToPropose) {
        rpFacade.addToProposedIngredients(ingredientToPropose);
    }

    @RequestMapping(method = RequestMethod.POST, value = "voteOnIngredient")
    public void voteOnIngredient(@RequestParam String ingredientName) {
        rpFacade.voteOnIngredient(ingredientName);
    }

    //ForAdmin
    @RequestMapping(method = RequestMethod.POST, value = "changeStatusAfterRecipeIsAdded")
    public void changeStatusAfterRecipeIsAdded(@RequestParam Long ingredientId) {
        rpFacade.changeStatusAfterRecipeIsAdded(ingredientId);
    }
}