package com.tahafurkan.sandbox.blogapplication.payload;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String fullName;
    private String email;
    private String body;
}
