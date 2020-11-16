package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.drink.DrinkDto;
import com.kodilla.drinks_backend.domain.drink.DrinkDto_Update;
import com.kodilla.drinks_backend.domain.drink.DrinkDto_Create;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDto;
import com.kodilla.drinks_backend.mapper.DrinkMapper;
import com.kodilla.drinks_backend.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/drink")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;
    @Autowired
    private DrinkMapper drinkMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/drinks")
    public List<DrinkDto> getAllDrinks() {
        return drinkMapper.mapToDrinkDtoList(drinkService.getAllDrinks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/drinks/{drinkId}")
    public DrinkDto getDrink(@RequestParam long drinkId) {
        return drinkMapper.mapToDrinkDto(drinkService.getDrink(drinkId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/drinks", consumes = APPLICATION_JSON_VALUE)
    public void createDrink(@RequestBody DrinkDto_Create drinkDto_create) {
        drinkService.createDrink(drinkMapper.mapToDrink_Create(drinkDto_create));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/drinks", consumes = APPLICATION_JSON_VALUE)
    public DrinkDto updateDrink(@RequestBody DrinkDto_Update drinkDto_update) {
        return drinkMapper.mapToDrinkDto(drinkService.updateDrink(drinkMapper.mapToDrink_Update(drinkDto_update)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/drinks/{drinkId}")
    public void deleteDrink(@RequestParam Long drinkId) {
        drinkService.deleteDrink(drinkId);
    }
}
