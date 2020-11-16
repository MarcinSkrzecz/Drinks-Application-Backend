package com.kodilla.drinks_backend.domain.ingredients;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface IngredientDao extends CrudRepository<Ingredient, Long> {
    @Override
    List<Ingredient> findAll();

    @Override
    Optional<Ingredient> findById(Long id);

    @Override
    Ingredient save (Ingredient ingredient);

    @Override
    void deleteById(Long id);
}
