package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.rating.Rating;
import com.kodilla.drinks_backend.domain.rating.RatingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {
    @Autowired
    private RatingDao ratingDao;

    @Autowired
    private CommentService commentService;


    public List<Rating> getAllRatings() {
        return ratingDao.findAll();
    }

    public Rating getRating(final Long id) {
        if (ratingDao.findById(id).isPresent()) {
            return ratingDao.findById(id).get();
        } else throw new IllegalArgumentException("Rating not found");
    }

    public void createRating(final Rating rating) {
        ratingDao.save(rating);
    }

    public void autoRatingUpdate(Long drinkId) {
        List<Comment> commentsList = commentService.getAllComments().stream()
                .filter(c -> c.getDrink().getId().equals(drinkId))
                .collect(Collectors.toList());
        double numberOfComments = commentsList.size();
        double sumOfRates = 0;
        String ratingString = "Not rated yet, be first one!";
        if (numberOfComments > 0) {
            for (int i = 0; i < numberOfComments; i++) {
                sumOfRates = sumOfRates + commentsList.get(i).getRate();
            }
            double roundedRating = Math.round(((sumOfRates / numberOfComments) * 100)) / 100;
            ratingString = String.valueOf(roundedRating);
        }
        Rating updatedRating = getAllRatings().stream()
                .filter(r -> r.getDrink().getId().equals(drinkId))
                .collect(Collectors.toList()).get(0);
        updatedRating.setRating(ratingString);

        ratingDao.save(updatedRating);
    }

    public void deleteRating(long id) {
        ratingDao.deleteById(id);
    }
}
