package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredientsDto;
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
    public ProposedIngredients mapToProposedIngredients_Create(final String proposedIngredient) {
        return new ProposedIngredients(
                proposedIngredient
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