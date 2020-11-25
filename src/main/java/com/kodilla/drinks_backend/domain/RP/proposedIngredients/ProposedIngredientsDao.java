package com.kodilla.drinks_backend.domain.RP.proposedIngredients;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProposedIngredientsDao extends CrudRepository<ProposedIngredients, Long> {
    @Override
    List<ProposedIngredients> findAll();
    @Override
    Optional<ProposedIngredients> findById(Long id);
    @Override
    ProposedIngredients save (ProposedIngredients proposedIngredients);
    @Override
    void deleteById(Long id);
}
