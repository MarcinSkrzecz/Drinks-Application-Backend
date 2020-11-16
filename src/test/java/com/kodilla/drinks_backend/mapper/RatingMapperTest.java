//package com.kodilla.drinks_backend.mapper;
//
//import com.kodilla.drinks_backend.domain.drink.Drink;
//import com.kodilla.drinks_backend.domain.rating.Rating;
//import com.kodilla.drinks_backend.domain.rating.RatingDto;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class RatingMapperTest {
//    @InjectMocks
//    private RatingMapper ratingMapper;
//
//    @Test
//    public void mapToRatingTest() {
//        //Given
//        RatingDto ratingDto = new RatingDto(0L, new Drink(),"10");
//
//        //When
//        Rating rating = ratingMapper.mapToRating(ratingDto);
//
//        //Then
//        assertEquals(ratingDto.getId(), rating.getId());
//        assertEquals(ratingDto.getDrink(),rating.getDrink());
//        assertEquals(ratingDto.getRating(), rating.getRating());
//    }
//    @Test
//    public void mapToDrinkDtoTest() {
//        //Given
//        Rating rating = new Rating(0L, new Drink(),"10");
//
//        //When
//        RatingDto ratingDto = ratingMapper.mapToRatingDto(rating);
//
//        //Then
//        assertEquals(ratingDto.getId(), rating.getId());
//        assertEquals(ratingDto.getDrink(),rating.getDrink());
//        assertEquals(ratingDto.getRating(), rating.getRating());
//    }
//    @Test
//    public void mapToRatingDtoListTest() {
//        //Given
//        List<Rating> ratings = new ArrayList<>();
//        ratings.add(new Rating(0L, new Drink(),"10"));
//        ratings.add(new Rating(1L, new Drink(),"10"));
//
//        //When
//        List<RatingDto> ratingDtos = ratingMapper.mapToRatingDtoList(ratings);
//
//        //Then
//        assertEquals(ratings.get(0).getId(), ratingDtos.get(0).getId());
//        assertEquals(ratings.get(0).getDrink(), ratingDtos.get(0).getDrink());
//        assertEquals(ratings.get(0).getRating(), ratingDtos.get(0).getRating());
//        assertEquals(ratings.get(1).getId(), ratingDtos.get(1).getId());
//        assertEquals(ratings.get(1).getDrink(), ratingDtos.get(1).getDrink());
//        assertEquals(ratings.get(1).getRating(), ratingDtos.get(1).getRating());
//    }
//}
