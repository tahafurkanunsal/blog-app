package com.tahafurkan.sandbox.blogapplication.service;

import com.tahafurkan.sandbox.blogapplication.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto update(CategoryDto categoryDto, Long categoryId);

    void delete(Long categoryId);

}