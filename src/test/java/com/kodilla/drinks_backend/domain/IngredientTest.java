package com.kodilla.drinks_backend.domain;

import com.kodilla.drinks_backend.domain.ingredients.Ingredient;
import com.kodilla.drinks_backend.domain.ingredients.IngredientDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IngredientTest {
    @Autowired
    private IngredientDao ingredientDao;

    @Test
    public void createDeleteIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredientDao.save(ingredient);
        assertTrue(ingredientDao.findById(ingredient.getId()).isPresent());
        ingredientDao.deleteById(ingredient.getId());
        assertFalse(ingredientDao.findById(ingredient.getId()).isPresent());
    }

    @Test
    public void createDeleteIngredients() {
        //Given
        Ingredient ingredient = new Ingredient();
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();

        //When
        ingredientDao.save(ingredient);
        ingredientDao.save(ingredient1);
        ingredientDao.save(ingredient2);

        //Then
        assertTrue(ingredientDao.findById(ingredient.getId()).isPresent());
        assertTrue(ingredientDao.findById(ingredient1.getId()).isPresent());
        assertTrue(ingredientDao.findById(ingredient2.getId()).isPresent());

        //CleanUp
        ingredientDao.deleteById(ingredient.getId());
        ingredientDao.deleteById(ingredient1.getId());
        ingredientDao.deleteById(ingredient2.getId());
        assertFalse(ingredientDao.findById(ingredient.getId()).isPresent());
        assertFalse(ingredientDao.findById(ingredient1.getId()).isPresent());
        assertFalse(ingredientDao.findById(ingredient2.getId()).isPresent());
    }

    @Test
    public void updateIngredients() {
        Ingredient ingredient = new Ingredient("Test_Description");
        ingredientDao.save(ingredient);
        assertTrue(ingredientDao.findById(ingredient.getId()).isPresent());
        assertEquals(ingredientDao.findById(ingredient.getId()).get().getDescription(),"Test_Description");
        ingredient.setDescription("Updated_Description");
        ingredientDao.save(ingredient);
        assertEquals(ingredientDao.findById(ingredient.getId()).get().getDescription(),"Updated_Description");
        ingredientDao.deleteById(ingredient.getId());
        assertFalse(ingredientDao.findById(ingredient.getId()).isPresent());
    }
}
