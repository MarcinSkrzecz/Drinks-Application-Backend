package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.ingredients.IngredientDto;
import com.kodilla.drinks_backend.facade.IngredientFacade;
import com.kodilla.drinks_backend.mapper.IngredientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class IngredientController {

    @Autowired
    private IngredientFacade ingredientFacade;
    @Autowired
    private IngredientMapper ingredientMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/ingredients")
    public List<IngredientDto> getAllIngredients() {
        return ingredientMapper.mapToIngredientDtoList(ingredientFacade.getAllIngredients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ingredients/{ingredientId}")
    public IngredientDto getIngredient(@RequestParam Long ingredientId) {
        return ingredientMapper.mapToIngredientDto(ingredientFacade.getIngredient(ingredientId));
    }
}
