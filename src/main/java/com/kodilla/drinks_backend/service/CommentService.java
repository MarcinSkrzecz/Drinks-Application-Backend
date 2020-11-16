package com.kodilla.drinks_backend.service;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.comment.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private RatingService ratingService;

    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }

    public Comment getComment(final Long id) {
        if (commentDao.findById(id).isPresent()) {
            return commentDao.findById(id).get();
        } else throw new IllegalArgumentException("Comment not found");
    }

    public void createComment(final Comment comment) {
        Comment createComment = comment;
        comment.setCreationDate(LocalDate.now());
        commentDao.save(comment);
        ratingService.autoRatingUpdate(comment.getDrink().getId());
    }

    public Comment updateComment(Comment comment) {
        Optional<Comment> commentDbRecord = commentDao.findById(comment.getId());

        if (commentDbRecord.isPresent()) {
            Comment commentForUpdate = commentDbRecord.get();

            commentForUpdate.setDrink(comment.getDrink());
            commentForUpdate.setUsername(comment.getUsername());
            commentForUpdate.setComment(comment.getComment());
            commentForUpdate.setRate(comment.getRate());
            commentForUpdate.setCreationDate(comment.getCreationDate());

            deleteComment(comment.getId());

            commentDao.save(commentForUpdate);

            ratingService.autoRatingUpdate(comment.getDrink().getId());
            return commentForUpdate;
        } else throw new IllegalArgumentException("Comment not found");
    }

    public void deleteComment(long id) {
        Long drinkId = getComment(id).getDrink().getId();
        commentDao.deleteById(id);
        ratingService.autoRatingUpdate(drinkId);
    }
}
