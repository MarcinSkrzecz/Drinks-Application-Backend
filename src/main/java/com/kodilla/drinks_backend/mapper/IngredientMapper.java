package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.ingredients.Ingredient;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDto;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDto_Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {

    public Ingredient mapToIngredient(final IngredientDto ingredientDto) {
        return new Ingredient(
                ingredientDto.getId(),
                ingredientDto.getDescription(),
                ingredientDto.getHowManyTimesUsed());
    }
    public IngredientDto mapToIngredientDto(final Ingredient ingredient) {
        if (ingredient.getId() == null) {
            throw new IllegalArgumentException("Ingredient not exist");
        } else {
            return new IngredientDto(
                    ingredient.getId(),
                    ingredient.getDescription(),
                    ingredient.getHowManyTimesUsed());
        }
    }
    public List<IngredientDto> mapToIngredientDtoList(final List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::mapToIngredientDto)
                .collect(Collectors.toList());
    }
    public Ingredient mapToIngredient_Update(final IngredientDto_Update ingredientDto_update) {
        return new Ingredient(
                ingredientDto_update.getId(),
                ingredientDto_update.getDescription());
    }
}