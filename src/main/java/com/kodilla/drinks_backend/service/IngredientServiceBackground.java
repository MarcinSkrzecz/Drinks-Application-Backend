package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.domain.ingredients.Ingredient;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientServiceBackground {

    @Autowired
    private IngredientDao ingredientDao;


    public boolean checkIfIngredientAlreadyExist(String description) {
        List<Ingredient> allIngredients = ingredientDao.findAll().stream()
                .filter(i -> i.getDescription().equals(description))
                .collect(Collectors.toList());
        if (allIngredients.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void createIngredient(String ingredientDescription) {
        Ingredient ingredientToCreate = new Ingredient(ingredientDescription, 1);
        ingredientDao.save(ingredientToCreate);
    }

    public void updateIngredient(String ingredientDescription) {
        Ingredient existingIngredient = ingredientDao.findAll().stream()
                .filter(i -> i.getDescription().equals(ingredientDescription))
                .collect(Collectors.toList()).get(0);
        Long id = existingIngredient.getId();
        ingredientDao.deleteById(id);

        int actualHowManyTimesUsed = existingIngredient.getHowManyTimesUsed();
        System.out.println(actualHowManyTimesUsed);
        int updatedHowManyTimesUsed = actualHowManyTimesUsed + 1;
        System.out.println(updatedHowManyTimesUsed);

        existingIngredient.setHowManyTimesUsed(updatedHowManyTimesUsed);
        ingredientDao.save(existingIngredient);
    }

    public List<String> separateStringsAndCorrect(String ingredients) {
        List<String> separatedListOfIngredients = Arrays.asList(ingredients.split("\\s*,\\s*"));
        List<String> correctedIngredientsList = new ArrayList<>();
        int size = separatedListOfIngredients.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String descriptionLowerCase = separatedListOfIngredients.get(i).toLowerCase();
                String descriptionCorrectFormat = descriptionLowerCase.substring(0, 1).toUpperCase() + descriptionLowerCase.substring(1);
                correctedIngredientsList.add(descriptionCorrectFormat);
            }
        }
        return correctedIngredientsList;
    }

    public void regenerateIngredients_DrinkCreate(String ingredients) {
        List<String> separatedIngredients = separateStringsAndCorrect(ingredients);
        if (separatedIngredients.size() > 0) {
            for (int i = 0; i < separatedIngredients.size(); i++) {
                String descriptionToCheck = separatedIngredients.get(i);
                boolean ifAlreadyExist = checkIfIngredientAlreadyExist(descriptionToCheck);

                if (ifAlreadyExist) {
                    updateIngredient(descriptionToCheck);
                } else {
                    createIngredient(descriptionToCheck);
                }
            }
        }
    }

    public void regenerateIngredients_DrinkUpdate(String newIngredients, String oldIngredients) {
        List<String> separatedOldIngredients = separateStringsAndCorrect(oldIngredients);
        int oldSize = separatedOldIngredients.size();
        List<String> separatedNewIngredients = separateStringsAndCorrect(newIngredients);
        int newSize = separatedNewIngredients.size();

        if (oldSize > 0) {
            for (int i = 0; i < oldSize; i++) {
                String description = separatedOldIngredients.get(i);
                Ingredient existingIngredient = ingredientDao.findAll().stream()
                        .filter(q -> q.getDescription().equals(description))
                        .collect(Collectors.toList()).get(0);
                Long id = existingIngredient.getId();
                ingredientDao.deleteById(id);
                int actualHowManyTimesUsed = existingIngredient.getHowManyTimesUsed();
                System.out.println(actualHowManyTimesUsed);
                int updatedHowManyTimesUsed = actualHowManyTimesUsed - 1;
                System.out.println(updatedHowManyTimesUsed);
                existingIngredient.setHowManyTimesUsed(updatedHowManyTimesUsed);
                if (updatedHowManyTimesUsed > 0) {
                    ingredientDao.save(existingIngredient);
                }
            }
        }
        if (newSize > 0) {
            for (int j = 0; j < newSize; j++) {
                String descriptionToCheck = separatedNewIngredients.get(j);
                boolean ifAlreadyExist = checkIfIngredientAlreadyExist(descriptionToCheck);

                if (ifAlreadyExist) {
                    updateIngredient(descriptionToCheck);
                } else {
                    createIngredient(descriptionToCheck);
                }
            }
        }
    }

    public void regenerateIngredients_DrinkDelete(String ingredients) {
        List<String> separatedIngredients = separateStringsAndCorrect(ingredients);
        int size = separatedIngredients.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String description = separatedIngredients.get(i);
                Ingredient existingIngredient = ingredientDao.findAll().stream()
                        .filter(q -> q.getDescription().equals(description))
                        .collect(Collectors.toList()).get(0);
                Long id = existingIngredient.getId();
                ingredientDao.deleteById(id);
                int actualHowManyTimesUsed = existingIngredient.getHowManyTimesUsed();
                System.out.println(actualHowManyTimesUsed);
                int updatedHowManyTimesUsed = actualHowManyTimesUsed - 1;
                System.out.println(updatedHowManyTimesUsed);
                existingIngredient.setHowManyTimesUsed(updatedHowManyTimesUsed);
                if (updatedHowManyTimesUsed > 0) {
                    ingredientDao.save(existingIngredient);
                }
            }
        }
    }
}
