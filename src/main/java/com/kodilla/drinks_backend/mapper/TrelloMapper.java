package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients.ProposedIngredientsService;
import com.kodilla.drinks_backend.service.DrinkService;
import com.kodilla.drinks_backend.domain.TrelloMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrelloMapper {

    @Autowired
    private DrinkService drinkService;
    @Autowired
    private ProposedIngredientsService proposedIngredientsService;

    public TrelloMessageDto mapDrinkDataToSend(final Long drinkToSendId) {
        Drink drinkToSend = drinkService.getDrink(drinkToSendId);

        Drink drink = new Drink.DrinkBuilder()
                .id(drinkToSend.getId())
                .username(drinkToSend.getUsername())
                .drinkName(drinkToSend.getDrinkName())
                .recipe(drinkToSend.getRecipe())
                .ingredients(drinkToSend.getIngredients())
                .build();
        System.out.println(drink);

        return new TrelloMessageDto(
                drinkToSend.getDrinkName(),
                drink.toString()
        );
    }

    public TrelloMessageDto mapProposedIngredientDataToSend(Long proposedIngredientId) {
        ProposedIngredients proposedIngredientsDB = proposedIngredientsService.getIngredient(proposedIngredientId);

        String title = "Drinks Application - Ingredient reached enough votes";
        String content = "Ingredient " + proposedIngredientsDB.getDescription() + " has reached 5 votes!";

        return new TrelloMessageDto(
                title,
                content
        );
    }
}