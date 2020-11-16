package com.kodilla.drinks_backend.domain.drink;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DrinkDao extends CrudRepository<Drink, Long> {
    @Override
    List<Drink> findAll();
    @Override
    Optional<Drink> findById(Long id);
    @Override
    Drink save (Drink drink);
    @Override
    void deleteById(Long id);
}
