package com.kodilla.drinks_backend.facade;

import com.kodilla.drinks_backend.domain.rating.Rating;
import com.kodilla.drinks_backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingFacade {

    @Autowired
    private RatingService ratingService;

    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    public Rating getRating(final Long id) {
        return ratingService.getRating(id);
    }

    public void createRating(final Rating rating) {
        ratingService.createRating(rating);
    }

    public void deleteRating(long id) {
        ratingService.deleteRating(id);
    }
}
