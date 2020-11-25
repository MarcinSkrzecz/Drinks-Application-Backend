package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import com.kodilla.drinks_backend.domain.rating.Rating;
import com.kodilla.drinks_backend.domain.rating.RatingDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RatingMapperTest {
    @Autowired
    private RatingMapper ratingMapper;
    @Autowired
    private DrinkDao drinkDao;

    @Test
    public void mapToRatingTest() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        RatingDto ratingDto = new RatingDto(0L, drink.getId(),"10");

        //When
        Rating rating = ratingMapper.mapToRating(ratingDto);

        //Then
        assertEquals(ratingDto.getId(), rating.getId());
        assertEquals(ratingDto.getDrinkId(),rating.getDrink().getId());
        assertEquals(ratingDto.getRating(), rating.getRating());
        //CleanUp
        drinkDao.deleteById(drink.getId());
    }
    @Test
    public void mapToRatingDtoTest() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        Rating rating = new Rating(0L, drink,"10");

        //When
        RatingDto ratingDto = ratingMapper.mapToRatingDto(rating);

        //Then
        assertEquals(ratingDto.getId(), rating.getId());
        assertEquals(ratingDto.getDrinkId(),rating.getDrink().getId());
        assertEquals(ratingDto.getRating(), rating.getRating());
        //CleanUp
        drinkDao.deleteById(drink.getId());
    }
    @Test
    public void mapToRatingDtoListTest() {
        //Given
        Drink drink = new Drink();
        drinkDao.save(drink);
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(0L, drink,"9"));
        ratings.add(new Rating(1L, drink,"10"));

        //When
        List<RatingDto> ratingDtos = ratingMapper.mapToRatingDtoList(ratings);

        //Then
        assertEquals(ratings.get(0).getId(), ratingDtos.get(0).getId());
        assertEquals(ratings.get(0).getDrink().getId(), ratingDtos.get(0).getDrinkId());
        assertEquals(ratings.get(0).getRating(), ratingDtos.get(0).getRating());
        assertEquals(ratings.get(1).getId(), ratingDtos.get(1).getId());
        assertEquals(ratings.get(1).getDrink().getId(), ratingDtos.get(1).getDrinkId());
        assertEquals(ratings.get(1).getRating(), ratingDtos.get(1).getRating());
    }
}
