package com.harshitha.inventory_management_backend.service;

import com.harshitha.inventory_management_backend.dto.request.CategoryRequest;
import com.harshitha.inventory_management_backend.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);

}