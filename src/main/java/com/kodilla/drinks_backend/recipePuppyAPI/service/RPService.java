package com.kodilla.drinks_backend.recipePuppyAPI.service;

import com.kodilla.drinks_backend.email_services.EmailSender;
import com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients.ProposedIngredientsDao;
import com.kodilla.drinks_backend.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RPService {
    @Autowired
    private RPServiceBackground rpServiceBackground;
    @Autowired
    private ProposedIngredientsDao proposedIngredientsDao;
    @Autowired
    private TrelloService trelloService;

    //To będzie jedna z list jako zwykłe propozycje - w vaadinie ta lista po kliknieciu pola bedzie jako vote
    //Plus fasada do filtrowania by to co juz jest w drinkach sie tu nie znajdowalo

    public List<String> getIngredientsFromRecipePuppyDB() {
        return rpServiceBackground.filterFromAlreadyExistingIngredientsInDrinks();
    }
////////////////////////////////////////////

    //To lista tych co zostały zaproponowane
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
}
