package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.rating.Rating;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {
    @Autowired
    private DrinkDao drinkDao;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientServiceBackground ingredientServiceBackground;
    @Autowired
    private RatingService ratingService;

    public List<Drink> getAllDrinks() {
        return drinkDao.findAll();
    }

    public Drink getDrink(final Long id) {
        if (drinkDao.findById(id).isPresent()) {
            return drinkDao.findById(id).get();
        } else throw new IllegalArgumentException("Drink not found");
    }

    public void createDrink(final Drink drink) {
        drinkDao.save(drink);
        ratingService.createRating(new Rating(drink));
        ingredientService.regenerateIngredients_DrinkCreate(drink.getIngredients());
    }

    public Drink updateDrink(Drink drink) {
        Optional<Drink> drinkDbRecord = drinkDao.findById(drink.getId());

        if (drinkDbRecord.isPresent()) {
            Drink drinkForUpdate = drinkDbRecord.get();

            String ingredientsBeforeUpdate =drinkForUpdate.getIngredients();

            drinkForUpdate.setUsername(drink.getUsername());
            drinkForUpdate.setDrinkName(drink.getDrinkName());
            drinkForUpdate.setRecipe(drink.getRecipe());
            drinkForUpdate.setIngredients(drink.getIngredients());

            drinkDao.save(drinkForUpdate);

            ingredientService.regenerateIngredients_DrinkUpdate(drink.getIngredients(), ingredientsBeforeUpdate);

            return drinkForUpdate;
        } else throw new IllegalArgumentException("Drink not found");
    }

    public void deleteDrink(long id) {
        String ingredients = getDrink(id).getIngredients();
        drinkDao.deleteById(id);
        ingredientService.regenerateIngredients_DrinkDelete(ingredients);
    }

    public List<String> getAllUsedIngredientsList() {
        List<Drink> allDrinks = getAllDrinks();
        int size = allDrinks.size();
        List<String> separatedIngredients = new ArrayList<>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String ingredientToCorrect = allDrinks.get(i).getIngredients();
                List<String> ingredients = ingredientServiceBackground.separateStringsAndCorrect(ingredientToCorrect);
                int ingredientsSize = ingredients.size();
                if (ingredientsSize > 0) {
                    for (int j = 0; j < ingredientsSize; j++) {
                        separatedIngredients.add(separatedIngredients.get(j));
                    }
                }
            }
        }
        return separatedIngredients;
    }
}
