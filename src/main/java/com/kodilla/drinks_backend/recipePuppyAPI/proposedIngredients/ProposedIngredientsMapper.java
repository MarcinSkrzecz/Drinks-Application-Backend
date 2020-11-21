package com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients;

import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.drink.DrinkDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProposedIngredientsMapper {

    public ProposedIngredients mapToProposedIngredients(final ProposedIngredientsDto proposedIngredientsDto) {
        return new ProposedIngredients(
                proposedIngredientsDto.getId(),
                proposedIngredientsDto.getDescription(),
                proposedIngredientsDto.getVotes(),
                proposedIngredientsDto.getStatus()
        );
    }

    public ProposedIngredientsDto mapToProposedIngredientsDto(final ProposedIngredients proposedIngredients) {
        return new ProposedIngredientsDto(
                proposedIngredients.getId(),
                proposedIngredients.getDescription(),
                proposedIngredients.getVotes(),
                proposedIngredients.getStatus()
        );
    }

    public List<ProposedIngredientsDto> mapToProposedIngredientsDtoList(final List<ProposedIngredients> proposedIngredients) {
        return proposedIngredients.stream()
                .map(this::mapToProposedIngredientsDto)
                .collect(Collectors.toList());
    }
}
