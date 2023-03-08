package com.tahafurkan.sandbox.blogapplication.service;

import com.tahafurkan.sandbox.blogapplication.entity.Post;
import com.tahafurkan.sandbox.blogapplication.payload.PostDto;
import com.tahafurkan.sandbox.blogapplication.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto create(PostDto postDto);

    PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto update(long id, PostDto postDto);

    void delete(long id);

    List<PostDto> getPostsByCategoryId(Long categoryId);
}