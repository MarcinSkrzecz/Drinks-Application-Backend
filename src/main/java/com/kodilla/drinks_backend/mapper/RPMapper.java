package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients.ProposedIngredientsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RPMapper {

    public ProposedIngredientsDto mapToProposedIngredientsDto(final ProposedIngredients proposedIngredients) {
        return new ProposedIngredientsDto(
                proposedIngredients.getId(),
                proposedIngredients.getDescription(),
                proposedIngredients.getVotes(),
                proposedIngredients.getStatus()
        );
    }

    public List<ProposedIngredientsDto> mapToIProposedIngredientsDtoList(final List<ProposedIngredients> proposedIngredients) {
        return proposedIngredients.stream()
                .map(this::mapToProposedIngredientsDto)
                .collect(Collectors.toList());
    }
}
