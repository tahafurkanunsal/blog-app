package com.tahafurkan.sandbox.blogapplication.service;

import com.tahafurkan.sandbox.blogapplication.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto create(long postId, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto update(long postId, long commentId, CommentDto commentResponse);

    void delete(long postId, long commentId);
}