package com.kodilla.drinks_backend.domain.RP.proposedIngredients;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProposedIngredientsDto {
    private Long id;
    private String description;
    private int votes;
    private String status;
}
