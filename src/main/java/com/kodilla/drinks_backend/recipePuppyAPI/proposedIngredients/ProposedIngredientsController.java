package com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/proposedIngredients")
public class ProposedIngredientsController {

    @Autowired
    private ProposedIngredientsService proposedIngredientsService;
    @Autowired
    private ProposedIngredientsMapper proposedIngredientsMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllProposedIngredients")
    public List<ProposedIngredientsDto> getAllProposedIngredients() {
        return proposedIngredientsMapper.mapToProposedIngredientsDtoList(proposedIngredientsService.getAllIngredients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProposedIngredient")
    public ProposedIngredientsDto getProposedIngredient(@RequestParam Long ingredientId) {
        return proposedIngredientsMapper.mapToProposedIngredientsDto(proposedIngredientsService.getIngredient(ingredientId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createIngredient", consumes = APPLICATION_JSON_VALUE)
    public void createIngredient(@RequestBody ProposedIngredientsDto proposedIngredientsDto) {
        proposedIngredientsService.createIngredient(proposedIngredientsMapper.mapToProposedIngredients(proposedIngredientsDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteIngredient")
    public void deleteIngredient(@RequestParam Long ingredientId) {
        proposedIngredientsService.deleteIngredient(ingredientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "voteOnIngredient")
    public void voteOnIngredient(@RequestParam Long ingredientId) {
        proposedIngredientsService.voteOnIngredient(ingredientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changeStatusToCompleted")
    public void changeStatusToCompleted(@RequestParam Long ingredientId) {
        proposedIngredientsService.changeStatusToCompleted(ingredientId);
    }
}
