package com.tahafurkan.sandbox.blogapplication.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;

    @NotEmpty(message = "Post title should not be null or empty")
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @NotEmpty(message = "Post description should not be null or empty")
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotEmpty(message = "Post content should not be null or empty")
    private String content;
    private Long categoryId;
    private Set<CommentDto> comments;

}