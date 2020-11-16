package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.ingredients.IngredientDto;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDto_Update;
import com.kodilla.drinks_backend.mapper.IngredientMapper;
import com.kodilla.drinks_backend.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientMapper ingredientMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllIngredients")
    public List<IngredientDto> getAllIngredients() {
        return ingredientMapper.mapToIngredientDtoList(ingredientService.getAllIngredients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getIngredient")
    public IngredientDto getIngredient(@RequestParam long ingredientId) {
        return ingredientMapper.mapToIngredientDto(ingredientService.getIngredient(ingredientId));
    }


//    @RequestMapping(method = RequestMethod.POST, value = "createIngredient")
//    public void createIngredients(@RequestParam String ingredients) {
//        ingredientService.createIngredients(ingredients);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "updateIngredient", consumes = APPLICATION_JSON_VALUE)
//    public void updateIngredients(@RequestBody IngredientDto_Update ingredientDto_update) {
//        ingredientService.updateIngredients(ingredientMapper.mapToIngredient_Update(ingredientDto_update));
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "deleteIngredient")
//    public void deleteIngredient(@RequestParam Long ingredientId) {
//        ingredientService.deleteIngredient(ingredientId);
//    }
}
