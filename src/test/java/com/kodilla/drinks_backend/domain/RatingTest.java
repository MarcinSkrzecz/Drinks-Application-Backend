package com.kodilla.drinks_backend.domain;

import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.rating.Rating;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import com.kodilla.drinks_backend.domain.rating.RatingDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RatingTest {
    @Autowired
    private RatingDao ratingDao;
    @Autowired
    private DrinkDao drinkDao;

    @Test
    public void createDeleteRatingWithDrink() {
        //Given
        Drink drink = new Drink();
        Rating rating = new Rating();
        rating.setDrink(drink);
        //When
        drinkDao.save(drink);
        ratingDao.save(rating);
        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(ratingDao.findById(rating.getId()).isPresent());
        //CleanUp
        drinkDao.deleteById(drink.getId());
        assertFalse(ratingDao.findById(rating.getId()).isPresent());
    }

    @Test
    public void updateDeleteRatingWithDrink() {
        //Given
        Drink drink = new Drink();
        Rating rating = new Rating();
        rating.setDrink(drink);
        //When
        drinkDao.save(drink);
        ratingDao.save(rating);
        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(ratingDao.findById(rating.getId()).isPresent());
        assertEquals(ratingDao.findById(rating.getId()).get().getRating(),"Not rated yet, be first one!");
        //Update
        rating.setRating("10");
        ratingDao.save(rating);
        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(ratingDao.findById(rating.getId()).isPresent());
        assertEquals(ratingDao.findById(rating.getId()).get().getRating(),"10");
        //CleanUp
        drinkDao.deleteById(drink.getId());
        assertFalse(drinkDao.findById(drink.getId()).isPresent());
        assertFalse(ratingDao.findById(rating.getId()).isPresent());
    }
}
