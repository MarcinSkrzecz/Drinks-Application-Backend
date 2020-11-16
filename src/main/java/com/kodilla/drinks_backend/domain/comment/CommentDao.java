package com.kodilla.drinks_backend.domain.comment;

import com.kodilla.drinks_backend.domain.comment.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {
    @Override
    List<Comment> findAll();
    @Override
    Optional<Comment> findById(Long id);
    @Override
    Comment save (Comment comment);
    @Override
    void deleteById(Long id);
}
