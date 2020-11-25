package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.ingredients.Ingredient;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDto;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDto_Update;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IngredientMapperTest {
    @InjectMocks
    private IngredientMapper ingredientMapper;

    @Test
    public void mapToIngredientTest() {
        //Given
        IngredientDto ingredientDto = new IngredientDto(0L, "Test_Description", 5);
        //When
        Ingredient ingredient = ingredientMapper.mapToIngredient(ingredientDto);
        //Then
        assertEquals(ingredientDto.getId(),ingredient.getId());
        assertEquals(ingredientDto.getDescription(), ingredient.getDescription());
        assertEquals(ingredientDto.getHowManyTimesUsed(), ingredient.getHowManyTimesUsed());
    }
    @Test
    public void mapToIngredientDtoTest() {
        //Given
        Ingredient ingredient = new Ingredient(0L, "Test_Description", 0);
        //When
        IngredientDto ingredientDto = ingredientMapper.mapToIngredientDto(ingredient);
        //Then
        assertEquals(ingredientDto.getId(),ingredient.getId());
        assertEquals(ingredientDto.getDescription(), ingredient.getDescription());
    }

    @Test
    public void mapToIngredientDtoList() {
        //Given
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient(0L, "Test_Description_0",0));
        ingredientList.add(new Ingredient(1L, "Test_Description_1",0));
        //When
        List<IngredientDto> ingredientDtoList = ingredientMapper.mapToIngredientDtoList(ingredientList);
        //Then
        assertEquals(2, ingredientDtoList.size());
        assertEquals(2, ingredientList.size());
        assertEquals(ingredientDtoList.get(0).getId(), ingredientList.get(0).getId());
        assertEquals(ingredientDtoList.get(0).getDescription(), ingredientList.get(0).getDescription());
        assertEquals(ingredientDtoList.get(1).getId(), ingredientList.get(1).getId());
        assertEquals(ingredientDtoList.get(1).getDescription(), ingredientList.get(1).getDescription());
    }
    @Test
    public void mapToIngredient_Update() {
        //Given
        IngredientDto_Update ingredientDto_update = new IngredientDto_Update(0L, "Test_Description");
        //When
        Ingredient ingredient = ingredientMapper.mapToIngredient_Update(ingredientDto_update);
        //Then
        assertEquals(ingredient.getId(),ingredientDto_update.getId());
        assertEquals(ingredient.getDescription(), ingredientDto_update.getDescription());
    }
}
