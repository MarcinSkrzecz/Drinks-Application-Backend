package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.rating.RatingDto;
import com.kodilla.drinks_backend.facade.RatingFacade;
import com.kodilla.drinks_backend.mapper.RatingMapper;
import com.kodilla.drinks_backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rating")
public class RatingController {

    @Autowired
    private RatingFacade ratingFacade;
    @Autowired
    private RatingMapper ratingMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllRatings")
    public List<RatingDto> getAllRatings() {
        return ratingMapper.mapToRatingDtoList(ratingFacade.getAllRatings());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRating")
    public RatingDto getRating(@RequestParam Long ratingId) {
        return ratingMapper.mapToRatingDto(ratingFacade.getRating(ratingId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteRating")
    public void deleteRating(@RequestParam Long ratingId) {
        ratingFacade.deleteRating(ratingId);
    }
}
