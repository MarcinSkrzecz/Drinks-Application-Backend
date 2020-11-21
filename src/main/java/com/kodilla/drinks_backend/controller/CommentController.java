package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.comment.CommentDto;
import com.kodilla.drinks_backend.domain.comment.CommentDto_Create;
import com.kodilla.drinks_backend.domain.comment.CommentDto_Update;
import com.kodilla.drinks_backend.facade.CommentFacade;
import com.kodilla.drinks_backend.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    @Autowired
    private CommentFacade commentFacade;
    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllComments")
    public List<CommentDto> getAllComments() {
        return commentMapper.mapToCommentDtoList(commentFacade.getAllComments());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getComment")
    public CommentDto getComment(@RequestParam Long commentId) {
        return commentMapper.mapToCommentDto(commentFacade.getComment(commentId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createComment", consumes = APPLICATION_JSON_VALUE)
    public void createComment(@RequestBody CommentDto_Create commentDto_create) {
        commentFacade.createComment(commentMapper.mapToComment_Create(commentDto_create));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateComment", consumes = APPLICATION_JSON_VALUE)
    public CommentDto updateComment(@RequestBody CommentDto_Update commentDto_update) {
        return commentMapper.mapToCommentDto(commentFacade.updateComment(commentMapper.mapToComment_Update(commentDto_update)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteComment")
    public void deleteComment(@RequestParam Long commentId) {
        commentFacade.deleteComment(commentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "likeComment")
    public void likeComment(@RequestParam Long commentId) {
        commentFacade.likeComment(commentId);
    }
}
