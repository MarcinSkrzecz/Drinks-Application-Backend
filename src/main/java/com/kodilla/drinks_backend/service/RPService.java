package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.client.RPClient;
import com.kodilla.drinks_backend.domain.RP.RecipesDto;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredientsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RPService {
    @Autowired
    private ProposedIngredientsDao proposedIngredientsDao;
    @Autowired
    private TrelloService trelloService;
    @Autowired
    private RPClient rpClient;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private DrinkService drinkService;

    public List<String> getIngredientsFromRecipePuppyDB() {
        return backgroundFilterFromAlreadyExistingIngredientsInDrinks();
    }

    public List<ProposedIngredients> getAllProposedIngredients() {
        return proposedIngredientsDao.findAll();
    }

    public ProposedIngredients getIngredientToPropose(final Long id) {
        if (proposedIngredientsDao.findById(id).isPresent()) {
            return proposedIngredientsDao.findById(id).get();
        } else throw new IllegalArgumentException("IngredientToPropose not found");
    }

    public void addToProposedIngredients(String ingredientToPropose) {
        proposedIngredientsDao.save(new ProposedIngredients(ingredientToPropose));
    }

    public void voteOnIngredient(String ingredientName) {
        List<ProposedIngredients> proposedIngredientsList = proposedIngredientsDao.findAll();
        ProposedIngredients proposedIngredient = proposedIngredientsList.stream()
                .filter(i -> i.getDescription().equals(ingredientName))
                .collect(Collectors.toList()).get(0);
        if (proposedIngredient == null) {
            addToProposedIngredients(ingredientName);
        } else {
            int actualVotes = proposedIngredient.getVotes();
            int updatedVotes = actualVotes + 1;
            proposedIngredientsDao.deleteById(proposedIngredient.getId());
            proposedIngredient.setVotes(updatedVotes);
            if (updatedVotes == 5) {
                proposedIngredient.setStatus("Send to create Drink");
                trelloService.sendProposedIngredientDataToTrello(proposedIngredient.getId());
            }
            proposedIngredientsDao.save(proposedIngredient);
        }
    }

    public void changeStatusAfterRecipeIsAdded(Long ingredientId) {
        ProposedIngredients proposedIngredients = getIngredientToPropose(ingredientId);
        proposedIngredientsDao.deleteById(ingredientId);
        proposedIngredients.setStatus("Drink Created");
        proposedIngredients.setAddedToRecipes(true);
        proposedIngredientsDao.save(proposedIngredients);
    }

    public List<String> backgroundGetIngredientsFromRecipes() {
        List<RecipesDto> recipesDtos = rpClient.getMainCard().getRecipes();
        List<String> ingredients = new ArrayList<>();
        int size = recipesDtos.size();
        if (size > 0) {
            for (RecipesDto recipesDto : recipesDtos) {
                ingredients.add(recipesDto.getIngredients());
            }
        }
        return ingredients;
    }

    public List<String> backgroundGetAllUniqueIngredients() {
        List<String> ingredients = backgroundGetIngredientsFromRecipes();
        List<String> ingredientsWithDuplicates = new ArrayList<>();
        int size = ingredients.size();
        if (size > 0) {
            for (String ingredientToCorrect : ingredients) {
                List<String> separatedListOfIngredients = ingredientService.separateStringsAndCorrect(ingredientToCorrect);
                int ingredientsSize = separatedListOfIngredients.size();
                if (ingredientsSize > 0) {
                    ingredientsWithDuplicates.addAll(separatedListOfIngredients);
                }
            }
        }
        List<String> ingredientsWithoutDuplicates = ingredientsWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        return ingredientsWithoutDuplicates;
    }

    public List<String> backgroundFilterFromAlreadyExistingIngredientsInDrinks() {
        List<String> rpIngredients = backgroundGetAllUniqueIngredients();
        int size1 = rpIngredients.size();

        List<String> ingredientsAlreadyInDrinks = drinkService.getAllUsedIngredientsList();

        List<String> uniqueIngredients = new ArrayList<>();

        for (int i = 0; i < size1; i++) {
            String checkedIngredient = rpIngredients.get(i);
            if (!ingredientsAlreadyInDrinks.contains(checkedIngredient)) {
                uniqueIngredients.add(checkedIngredient);
            }
        }
        return uniqueIngredients;
    }
}