package com.kodilla.drinks_backend.domain;

import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredientsDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProposedIngredientsTest {

    @Autowired
    private ProposedIngredientsDao proposedIngredientsDao;

    @Test
    public void testCreateDelete() {
        //Given
        ProposedIngredients proposedIngredients = new ProposedIngredients();
        //When
        proposedIngredientsDao.save(proposedIngredients);
        //Then
        assertTrue(proposedIngredientsDao.findById(proposedIngredients.getId()).isPresent());
        //CleanUp
        proposedIngredientsDao.deleteById(proposedIngredients.getId());
        assertFalse(proposedIngredientsDao.findById(proposedIngredients.getId()).isPresent());
    }
    @Test
    public void testUpdate() {
        //Given
        ProposedIngredients proposedIngredients = new ProposedIngredients();
        //When
        proposedIngredientsDao.save(proposedIngredients);
        //Then
        assertTrue(proposedIngredientsDao.findById(proposedIngredients.getId()).isPresent());
        //Update
        proposedIngredients.setDescription("TD");
        proposedIngredientsDao.save(proposedIngredients);
        //Then
        assertTrue(proposedIngredientsDao.findById(proposedIngredients.getId()).isPresent());
        assertEquals(proposedIngredients.getDescription(),"TD");
        //CleanUp
        proposedIngredientsDao.deleteById(proposedIngredients.getId());
        assertFalse(proposedIngredientsDao.findById(proposedIngredients.getId()).isPresent());
    }
}
