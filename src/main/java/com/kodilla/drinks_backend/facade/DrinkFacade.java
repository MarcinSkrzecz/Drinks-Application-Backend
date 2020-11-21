package com.kodilla.drinks_backend.facade;

import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DrinkFacade {

    @Autowired
    private DrinkService drinkService;

    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }
    public Drink getDrink(final Long id) {
        return drinkService.getDrink(id);
    }
    public void createDrink(final Drink drink) {
        drinkService.createDrink(drink);
    }
    public Drink updateDrink(Drink drink) {
        return drinkService.updateDrink(drink);
    }
    public void deleteDrink(long id) {
        drinkService.deleteDrink(id);
    }
}
