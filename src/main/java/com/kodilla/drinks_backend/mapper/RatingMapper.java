package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import com.kodilla.drinks_backend.domain.rating.Rating;
import com.kodilla.drinks_backend.domain.rating.RatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingMapper {

    @Autowired
    private DrinkDao drinkDao;

    public Rating mapToRating(final RatingDto ratingDto) {
        if (ratingDto.getId() == null) {
            return new Rating();
        } else {
            return new Rating(
                    ratingDto.getId(),
                    drinkDao.findById(ratingDto.getDrinkId()).get(),
                    ratingDto.getRating());
        }
    }

    public RatingDto mapToRatingDto(final Rating rating) {
        if (rating.getId() == null) {
            throw new IllegalArgumentException("Rating not exist");
        } else {
            return new RatingDto(
                    rating.getId(),
                    rating.getDrink().getId(),
                    rating.getRating());
        }
    }

    public List<RatingDto> mapToRatingDtoList(final List<Rating> ratings) {
        return ratings.stream()
                .map(this::mapToRatingDto)
                .collect(Collectors.toList());
    }
}
