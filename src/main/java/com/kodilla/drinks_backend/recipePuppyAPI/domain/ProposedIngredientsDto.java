package com.kodilla.drinks_backend.recipePuppyAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProposedIngredientsDto {
    private Long id;
    private String description;
    private int votes;
    private String status;

    public ProposedIngredientsDto(String description) {
        this.description = description;
    }
}
