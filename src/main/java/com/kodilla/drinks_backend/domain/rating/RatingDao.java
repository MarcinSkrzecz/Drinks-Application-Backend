package com.kodilla.drinks_backend.domain.rating;

import com.kodilla.drinks_backend.domain.rating.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RatingDao extends CrudRepository<Rating, Long> {
    @Override
    List<Rating> findAll();

    @Override
    Optional<Rating> findById(Long id);

    @Override
    Rating save (Rating rating);

    @Override
    void deleteById(Long id);
}
