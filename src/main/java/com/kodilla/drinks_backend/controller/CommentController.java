package com.kodilla.drinks_backend.controller;

import com.kodilla.drinks_backend.domain.comment.CommentDto;
import com.kodilla.drinks_backend.domain.comment.CommentDto_Create;
import com.kodilla.drinks_backend.mapper.CommentMapper;
import com.kodilla.drinks_backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllComments")
    public List<CommentDto> getAllComments() {
        return commentMapper.mapToCommentDtoList(commentService.getAllComments());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getComment")
    public CommentDto getComment(@RequestParam long commentId) {
        return commentMapper.mapToCommentDto(commentService.getComment(commentId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createComment", consumes = APPLICATION_JSON_VALUE)
    public void createComment(@RequestBody CommentDto_Create commentDto_create) {
        commentService.createComment(commentMapper.mapToComment_Create(commentDto_create));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateComment", consumes = APPLICATION_JSON_VALUE)
    public CommentDto updateComment(@RequestBody CommentDto commentDto) {
        return commentMapper.mapToCommentDto(commentService.updateComment(commentMapper.mapToComment(commentDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteComment")
    public void deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
    }
}
