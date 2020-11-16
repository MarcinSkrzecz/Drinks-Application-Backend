package com.kodilla.drinks_backend.domain;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.rating.Rating;
import com.kodilla.drinks_backend.domain.comment.CommentDao;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import com.kodilla.drinks_backend.domain.rating.RatingDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DrinkTest {
    @Autowired
    private DrinkDao drinkDao;
    @Autowired
    private RatingDao ratingDao;
    @Autowired
    private CommentDao commentDao;

    @Test
    public void testCreateDeleteDrinkWithConnectedTables() {
        //Given
        Drink drink = new Drink();
        Rating rating = new Rating();
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        rating.setDrink(drink);
        comment1.setDrink(drink);
        comment2.setDrink(drink);
        //When
        drinkDao.save(drink);
        ratingDao.save(rating);
        commentDao.save(comment1);
        commentDao.save(comment2);
        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(ratingDao.findById(rating.getId()).isPresent());
        assertTrue(commentDao.findById(comment1.getId()).isPresent());
        assertTrue(commentDao.findById(comment2.getId()).isPresent());
        //CleanUp
        drinkDao.deleteById(drink.getId());
        assertFalse(ratingDao.findById(rating.getId()).isPresent());
        assertFalse(commentDao.findById(comment1.getId()).isPresent());
        assertFalse(commentDao.findById(comment2.getId()).isPresent());
    }
    @Test
    public void testUpdateDrinkWithConnectedTables() {
        //Given
        Drink drink = new Drink();
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        comment1.setDrink(drink);
        comment2.setDrink(drink);

        //When
        drinkDao.save(drink);
        commentDao.save(comment1);
        commentDao.save(comment2);

        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(commentDao.findById(comment1.getId()).isPresent());
        assertTrue(commentDao.findById(comment2.getId()).isPresent());

        //Update
        Comment commentToUpdate = new Comment();
        commentToUpdate.setDrink(drink);
        drinkDao.save(drink);
        commentDao.save(commentToUpdate);

        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(commentDao.findById(comment1.getId()).isPresent());
        assertTrue(commentDao.findById(comment2.getId()).isPresent());
        assertTrue(commentDao.findById(commentToUpdate.getId()).isPresent());

        //CleanUp
        drinkDao.deleteById(drink.getId());
        assertFalse(commentDao.findById(comment1.getId()).isPresent());
        assertFalse(commentDao.findById(comment2.getId()).isPresent());
    }
}
