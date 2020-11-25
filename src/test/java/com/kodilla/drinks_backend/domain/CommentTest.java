package com.kodilla.drinks_backend.domain;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.comment.CommentDao;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private DrinkDao drinkDao;

    @Test
    public void CreateDeleteCommentWithDrink() {
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
        //CleanUp
        drinkDao.deleteById(drink.getId());
        assertFalse(drinkDao.findById(drink.getId()).isPresent());
        assertFalse(commentDao.findById(comment1.getId()).isPresent());
        assertFalse(commentDao.findById(comment2.getId()).isPresent());
    }

    @Test
    public void updateDeleteCommentWithDrink() {
        //Given
        Drink drink = new Drink();
        Comment comment = new Comment("Test_Username","Test_Comment",10);
        comment.setDrink(drink);
        //When
        drinkDao.save(drink);
        commentDao.save(comment);
        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(commentDao.findById(comment.getId()).isPresent());
        assertEquals(commentDao.findById(comment.getId()).get().getUsername(),"Test_Username");
        assertEquals(commentDao.findById(comment.getId()).get().getComment(),"Test_Comment");
        assertEquals(commentDao.findById(comment.getId()).get().getRate(),10);
        //Update
        comment.setUsername("Updated_Username");
        comment.setComment("Updated_Comment");
        comment.setRate(5);
        commentDao.save(comment);
        //Then
        assertTrue(drinkDao.findById(drink.getId()).isPresent());
        assertTrue(commentDao.findById(comment.getId()).isPresent());
        assertEquals(commentDao.findById(comment.getId()).get().getUsername(),"Updated_Username");
        assertEquals(commentDao.findById(comment.getId()).get().getComment(),"Updated_Comment");
        assertEquals(commentDao.findById(comment.getId()).get().getRate(),5);
        //CleanUp
        drinkDao.deleteById(drink.getId());
        assertFalse(drinkDao.findById(drink.getId()).isPresent());
        assertFalse(commentDao.findById(comment.getId()).isPresent());
    }
}
