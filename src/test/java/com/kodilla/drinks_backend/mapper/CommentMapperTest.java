package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.comment.CommentDto;
import com.kodilla.drinks_backend.domain.comment.CommentDto_Create;
import com.kodilla.drinks_backend.domain.comment.CommentDto_Update;
import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CommentMapperTest {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private DrinkDao drinkDao;

    @Test
    public void mapToCommentTest() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        CommentDto commentDto = new CommentDto(0L,drink.getId(),"TU","TC",10, 5, LocalDate.now());
        //When
        Comment comment = commentMapper.mapToComment(commentDto);
        //Then
        assertEquals(comment.getId(), commentDto.getId());
        assertEquals(comment.getDrink().getId(), commentDto.getDrinkId());
        assertEquals(comment.getUsername(), commentDto.getUsername());
        assertEquals(comment.getComment(), commentDto.getComment());
        assertEquals(comment.getRate(), commentDto.getRate());
        assertEquals(comment.getLikes(), commentDto.getLikes());
        assertEquals(comment.getCreationDate(), commentDto.getCreationDate());
        //CleanUp
        drinkDao.deleteById(drink.getId());
    }
    @Test
    public void mapToCommentDtoTest() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        Comment comment = new Comment(0L,drink,"TU","TC",10, 5, LocalDate.now());
        //When
        CommentDto commentDto = commentMapper.mapToCommentDto(comment);
        //Then
        assertEquals(comment.getId(), commentDto.getId());
        assertEquals(comment.getDrink().getId(), commentDto.getDrinkId());
        assertEquals(comment.getUsername(), commentDto.getUsername());
        assertEquals(comment.getComment(), commentDto.getComment());
        assertEquals(comment.getRate(), commentDto.getRate());
        assertEquals(comment.getLikes(), commentDto.getLikes());
        assertEquals(comment.getCreationDate(), commentDto.getCreationDate());
        //CleanUp
        drinkDao.deleteById(drink.getId());
    }
    @Test
    public void mapToCommentDtoListTest() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        Comment comment1 = new Comment(0L, drink, "TU", "TC", 10, 5, LocalDate.now());
        Comment comment2 = new Comment(1L, drink, "TU", "TC", 10, 5, LocalDate.now());
        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
        //When
        List<CommentDto> commentDtos = commentMapper.mapToCommentDtoList(comments);
        //Then
        assertEquals(comments.get(0).getId(), commentDtos.get(0).getId());
        assertEquals(comments.get(0).getDrink().getId(), commentDtos.get(0).getDrinkId());
        assertEquals(comments.get(0).getUsername(), commentDtos.get(0).getUsername());
        assertEquals(comments.get(0).getComment(), commentDtos.get(0).getComment());
        assertEquals(comments.get(0).getRate(), commentDtos.get(0).getRate());
        assertEquals(comments.get(0).getLikes(), commentDtos.get(0).getLikes());
        assertEquals(comments.get(0).getCreationDate(), commentDtos.get(0).getCreationDate());

        assertEquals(comments.get(1).getId(), commentDtos.get(1).getId());
        assertEquals(comments.get(1).getDrink().getId(), commentDtos.get(1).getDrinkId());
        assertEquals(comments.get(1).getUsername(), commentDtos.get(1).getUsername());
        assertEquals(comments.get(1).getComment(), commentDtos.get(1).getComment());
        assertEquals(comments.get(1).getRate(), commentDtos.get(1).getRate());
        assertEquals(comments.get(1).getLikes(), commentDtos.get(1).getLikes());
        assertEquals(comments.get(1).getCreationDate(), commentDtos.get(1).getCreationDate());

        assertEquals(comments.size(), commentDtos.size());
        //CleanUp
        drinkDao.deleteById(drink.getId());
    }

    public void mapToComment_CreateTest() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        CommentDto_Create commentDto_create = new CommentDto_Create(drink.getId(), "TU", "TC", 10);
        //When
        Comment comment = commentMapper.mapToComment_Create(commentDto_create);
        //Then
        assertEquals(comment.getDrink().getId(), commentDto_create.getDrinkId());
        assertEquals(comment.getUsername(), commentDto_create.getUsername());
        assertEquals(comment.getComment(), commentDto_create.getComment());
        assertEquals(comment.getRate(), commentDto_create.getRate());
        //CleanUp
        drinkDao.deleteById(0L);
    }

    public void mapToComment_Update() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        CommentDto_Update commentDto_update = new CommentDto_Update(0L, drink.getId(), "TC", 10);
        //When
        Comment comment = commentMapper.mapToComment_Update(commentDto_update);
        //Then
        assertEquals(comment.getId(), commentDto_update.getId());
        assertEquals(comment.getDrink().getId(), commentDto_update.getDrinkId());
        assertEquals(comment.getComment(), commentDto_update.getComment());
        assertEquals(comment.getRate(), commentDto_update.getRate());
        //CleanUp
        drinkDao.deleteById(drink.getId());
    }
}
