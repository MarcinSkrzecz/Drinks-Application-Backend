package com.kodilla.drinks_backend.recipePuppyAPI.service;

import com.kodilla.drinks_backend.recipePuppyAPI.RPClient;
import com.kodilla.drinks_backend.recipePuppyAPI.domain.MainApiDto;
import com.kodilla.drinks_backend.recipePuppyAPI.domain.RecipesDto;
import com.kodilla.drinks_backend.service.DrinkService;
import com.kodilla.drinks_backend.service.IngredientServiceBackground;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class RPServiceBackground {
    @Autowired
    private RPClient RPClient;
    @Autowired
    private IngredientServiceBackground ingredientServiceBackground;
    @Autowired
    private DrinkService drinkService;


    public MainApiDto getMainApi() {
        System.out.println("Before getMainAPi");
        List<MainApiDto> mainApiDtoList = RPClient.getMainCard();
        System.out.println("list");
        int size = mainApiDtoList.size();
        System.out.println(size);
        return mainApiDtoList.get(0);
    }

    public List<RecipesDto> getRecipes() {
        System.out.println("After get main api");
        return getMainApi().getRecipes();
    }

    public List<String> getIngredientsFromRecipes() {
        System.out.println("after get ingredients from recipes");
        List<RecipesDto> recipesDtos = getRecipes();
        List<String> ingredients = new ArrayList<>();
        int size = recipesDtos.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                ingredients.add(recipesDtos.get(i).getIngredients());
            }
        }
        return ingredients;
    }

    public List<String> getAllUniqueIngredients() {
        List<String> ingredients = getIngredientsFromRecipes();
        List<String> ingredientsWithDuplicates = new ArrayList<>();
        int size = ingredients.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String ingredientToCorrect = ingredients.get(i);
                List<String> separatedListOfIngredients = ingredientServiceBackground.separateStringsAndCorrect(ingredientToCorrect);
                int ingredientsSize = separatedListOfIngredients.size();
                if (ingredientsSize > 0) {
                    for (int j = 0; j < ingredientsSize; j++) {
                        ingredientsWithDuplicates.add(separatedListOfIngredients.get(j));
                    }
                }
            }
        }
        List<String> ingredientsWithoutDuplicates = ingredientsWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        return ingredientsWithoutDuplicates;
    }

    public List<String> filterFromAlreadyExistingIngredientsInDrinks() {
        List<String> ingredientsFromDB = getAllUniqueIngredients();
        int ingredientsFromDBSize = ingredientsFromDB.size();
        List<String> ingredientsAlreadyInDrinks = drinkService.getAllUsedIngredientsList();

        List<String> ingredientsToShow = new ArrayList<>();

        for (int i = 0; i < ingredientsFromDBSize; i++) {
            String proposedIngredient = ingredientsFromDB.get(i);
            String ifExist = ingredientsAlreadyInDrinks.stream()
                    .filter(j -> j.equals(proposedIngredient))
                    .collect(Collectors.toList()).get(0);
            if (ifExist == null) {
                ingredientsToShow.add(ifExist);
            }
        }
        ingredientsToShow.stream()
                .distinct()
                .collect(Collectors.toList());

        return ingredientsToShow;
    }
}
