package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.config.ProposedIngredientsConfig;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredientsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposedIngredientsService {

    @Autowired
    private ProposedIngredientsDao proposedIngredientsDao;
    @Autowired
    private ProposedIngredientsConfig proposedIngredientsConfig;

    public List<ProposedIngredients> getAllIngredients() {
        List<ProposedIngredients> proposedIngredientsDB = proposedIngredientsDao.findAll();
        List<ProposedIngredients> filteredByStatusIfCollectingVotes = proposedIngredientsDB.stream()
                .filter(p -> p.getStatus().equals("Collecting votes"))
                .collect(Collectors.toList());
        List<ProposedIngredients> filteredByStatusIfVotesCollected = proposedIngredientsDB.stream()
                .filter(p -> p.getStatus().equals("Votes collected - WIP"))
                .collect(Collectors.toList());

        List<ProposedIngredients> listToShow = new ArrayList<>();
        listToShow.addAll(filteredByStatusIfCollectingVotes);
        listToShow.addAll(filteredByStatusIfVotesCollected);

        return listToShow;
    }

    public ProposedIngredients getIngredient(Long id) {
        if (proposedIngredientsDao.findById(id).isPresent()) {
            return proposedIngredientsDao.findById(id).get();
        } else throw new IllegalArgumentException("Ingredient not found");
    }

    public void createIngredient(final ProposedIngredients proposedIngredients) {
        proposedIngredientsDao.save(proposedIngredients);
    }

    public void deleteIngredient(final Long id) {
        proposedIngredientsDao.deleteById(id);
    }

    public void voteOnIngredient(Long id) {
        ProposedIngredients proposedIngredientsDB = getIngredient(id);

        deleteIngredient(id);

        int votes = proposedIngredientsDB.getVotes();
        int votesActual = votes + 1;

        if (votesActual == proposedIngredientsConfig.getCountToChangeStatus()) {
            proposedIngredientsDB.setDescription("Votes collected - WIP");
        }

        proposedIngredientsDB.setVotes(votesActual);

        createIngredient(proposedIngredientsDB);
    }

    public void changeStatusToCompleted(final Long id) {
        ProposedIngredients proposedIngredientsDB = getIngredient(id);

        deleteIngredient(id);

        proposedIngredientsDB.setDescription("Completed");

        createIngredient(proposedIngredientsDB);
    }
}