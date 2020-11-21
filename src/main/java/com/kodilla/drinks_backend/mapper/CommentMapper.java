package com.kodilla.drinks_backend.mapper;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.comment.CommentDto;
import com.kodilla.drinks_backend.domain.comment.CommentDto_Create;
import com.kodilla.drinks_backend.domain.comment.CommentDto_Update;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    @Autowired
    private DrinkDao drinkDao;

    public Comment mapToComment(final CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                drinkDao.findById(commentDto.getDrinkId()).get(),
                commentDto.getUsername(),
                commentDto.getComment(),
                commentDto.getRate());
    }

    public CommentDto mapToCommentDto(final Comment comment) {
        if (comment.getId() == null) {
            throw new IllegalArgumentException("Comment not exist");
        } else {
            return new CommentDto(
                    comment.getId(),
                    comment.getDrink().getId(),
                    comment.getUsername(),
                    comment.getComment(),
                    comment.getRate(),
                    comment.getLikes(),
                    comment.getCreationDate());
        }
    }

    public List<CommentDto> mapToCommentDtoList(final List<Comment> comments) {
        return comments.stream()
                .map(this::mapToCommentDto)
                .collect(Collectors.toList());
    }

    public Comment mapToComment_Create(final CommentDto_Create commentDto_create) {
        return new Comment(
                drinkDao.findById(commentDto_create.getDrinkId()).get(),
                commentDto_create.getUsername(),
                commentDto_create.getComment(),
                commentDto_create.getRate());
    }

    public Comment mapToComment_Update(final CommentDto_Update commentDto_update) {
        return new Comment(
                commentDto_update.getId(),
                drinkDao.findById(commentDto_update.getDrinkId()).get(),
                commentDto_update.getComment(),
                commentDto_update.getRate());
    }
}