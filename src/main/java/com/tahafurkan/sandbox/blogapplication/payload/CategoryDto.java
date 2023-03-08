package com.tahafurkan.sandbox.blogapplication.payload;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
