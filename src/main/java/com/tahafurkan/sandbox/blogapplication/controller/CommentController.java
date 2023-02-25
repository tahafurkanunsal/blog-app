package com.tahafurkan.sandbox.blogapplication.controller;

import com.tahafurkan.sandbox.blogapplication.payload.CommentDto;
import com.tahafurkan.sandbox.blogapplication.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(value = "/posts/{postId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable(name = "postId") long postId,
                                             @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.create(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(name = "postId") long postId) {
        return commentService.getCommentByPostId(postId);
    }

    @GetMapping(value = "posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId,
                                                     @PathVariable(name = "commentId") long commentId) {
        CommentDto commentDto = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping(value = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable(name = "postId") long postId,
                                             @PathVariable(name = "commentId") long commentId,
                                             @Valid @RequestBody CommentDto commentResponse) {

        return new ResponseEntity<>(commentService.update(postId, commentId, commentResponse), HttpStatus.OK);

    }

    @DeleteMapping(value = "/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> delete(@PathVariable(name = "postId") long postId,
                                         @PathVariable(name = "commentId") long commentId) {
        commentService.delete(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfuly", HttpStatus.OK);


    }
}
