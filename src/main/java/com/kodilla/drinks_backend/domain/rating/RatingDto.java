package com.kodilla.drinks_backend.domain.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RatingDto {
    private Long id;
    private Long drinkId;
    private String rating;
}
