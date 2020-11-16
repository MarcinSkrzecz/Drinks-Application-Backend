package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.drink.DrinkDto;
import com.kodilla.drinks_backend.domain.drink.DrinkDto_Update;
import com.kodilla.drinks_backend.domain.drink.DrinkDto_Create;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrinkMapper {

    public Drink mapToDrink(final DrinkDto drinkDto) {
        return new Drink(
                drinkDto.getId(),
                drinkDto.getUsername(),
                drinkDto.getDrinkName(),
                drinkDto.getRecipe(),
                drinkDto.getIngredients());
    }

    public DrinkDto mapToDrinkDto(final Drink drink) {
        if (drink.getId() == null) {
            throw new IllegalArgumentException("Drink not exist");
        } else {
            return new DrinkDto(
                    drink.getId(),
                    drink.getUsername(),
                    drink.getDrinkName(),
                    drink.getRecipe(),
                    drink.getIngredients(),
                    drink.getCreationDate());
        }
    }

    public List<DrinkDto> mapToDrinkDtoList(final List<Drink> drinks) {
        return drinks.stream()
                .map(this::mapToDrinkDto)
                .collect(Collectors.toList());
    }

    public Drink mapToDrink_Create(final DrinkDto_Create drinkDto_create) {
        return new Drink(
            drinkDto_create.getUsername(),
            drinkDto_create.getDrinkName(),
            drinkDto_create.getRecipe(),
            drinkDto_create.getIngredients());
    }

    public Drink mapToDrink_Update(final DrinkDto_Update drinkDto_update) {
        if (drinkDto_update.getId() == null) {
            throw new IllegalArgumentException("Drink not exist");
        } else {
            return new Drink(
                    drinkDto_update.getId(),
                    drinkDto_update.getUsername(),
                    drinkDto_update.getDrinkName(),
                    drinkDto_update.getRecipe(),
                    drinkDto_update.getIngredients());
        }
    }
}
