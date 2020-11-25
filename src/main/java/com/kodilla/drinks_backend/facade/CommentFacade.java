package com.kodilla.drinks_backend.facade;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentFacade {
    @Autowired
    private CommentService commentService;

    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
    public Comment getComment(final Long id) {
        return commentService.getComment(id);
    }
    public void createComment(final Comment comment) {
        commentService.createComment(comment);
    }
    public Comment updateComment(Comment comment) {
        return commentService.updateComment(comment);
    }
    public void deleteComment(long id) {
        commentService.deleteComment(id);
    }
    public void likeComment(Long id) {
        commentService.likeComment(id);
    }
}