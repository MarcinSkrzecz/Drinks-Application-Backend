package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredientsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProposedIngredientsMapperTest {
    @InjectMocks
    private ProposedIngredientsMapper proposedIngredientsMapper;

    @Test
    public void mapToProposedIngredientsTest() {
        //Given
        ProposedIngredientsDto proposedIngredientsDto = new ProposedIngredientsDto(0L, "TD",5,"TS");
        //When
        ProposedIngredients proposedIngredients = proposedIngredientsMapper.mapToProposedIngredients(proposedIngredientsDto);
        //Then
        assertEquals(proposedIngredients.getId(), proposedIngredientsDto.getId());
        assertEquals(proposedIngredients.getDescription(), proposedIngredientsDto.getDescription());
        assertEquals(proposedIngredients.getVotes(), proposedIngredientsDto.getVotes());
        assertEquals(proposedIngredients.getStatus(), proposedIngredientsDto.getStatus());
    }

    @Test
    public void mapToProposedIngredientsDtoTest() {
        //Given
        ProposedIngredients proposedIngredients = new ProposedIngredients(0L, "TD",5,"TS");
        //When
        ProposedIngredientsDto proposedIngredientsDto = proposedIngredientsMapper.mapToProposedIngredientsDto(proposedIngredients);
        //Then
        assertEquals(proposedIngredients.getId(), proposedIngredientsDto.getId());
        assertEquals(proposedIngredients.getDescription(), proposedIngredientsDto.getDescription());
        assertEquals(proposedIngredients.getVotes(), proposedIngredientsDto.getVotes());
        assertEquals(proposedIngredients.getStatus(), proposedIngredientsDto.getStatus());
    }

    @Test
    public void mapToProposedIngredientsDtoListTest() {
        //Given
        List<ProposedIngredients> proposedIngredientsList = new ArrayList<>();
        proposedIngredientsList.add(new ProposedIngredients(0L, "TD",5,"TS"));
        //When
        List<ProposedIngredientsDto> proposedIngredientsDtoList = proposedIngredientsMapper.mapToProposedIngredientsDtoList(proposedIngredientsList);
        //Then
        assertEquals(proposedIngredientsDtoList.get(0).getId(), proposedIngredientsList.get(0).getId());
        assertEquals(proposedIngredientsDtoList.get(0).getDescription(), proposedIngredientsList.get(0).getDescription());
        assertEquals(proposedIngredientsDtoList.get(0).getVotes(), proposedIngredientsList.get(0).getVotes());
        assertEquals(proposedIngredientsDtoList.get(0).getStatus(), proposedIngredientsList.get(0).getStatus());
        assertEquals(proposedIngredientsDtoList.size(), proposedIngredientsList.size());

    }
}