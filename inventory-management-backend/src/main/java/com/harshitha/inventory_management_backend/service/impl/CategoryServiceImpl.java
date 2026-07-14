package com.harshitha.inventory_management_backend.service.impl;

import com.harshitha.inventory_management_backend.dto.request.CategoryRequest;
import com.harshitha.inventory_management_backend.dto.response.CategoryResponse;
import com.harshitha.inventory_management_backend.entity.Category;
import com.harshitha.inventory_management_backend.exception.CategoryNotFoundException;
import com.harshitha.inventory_management_backend.exception.DuplicateCategoryException;
import com.harshitha.inventory_management_backend.repository.CategoryRepository;
import com.harshitha.inventory_management_backend.service.CategoryService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new DuplicateCategoryException("Category already exists with name : " + request.getName());
        }
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        Category savedCategory = categoryRepository.save(category);

        return CategoryResponse.builder()
            .id(savedCategory.getId())
            .name(savedCategory.getName())
            .description(savedCategory.getDescription())
            .build();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id : " + id));

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build())
                .toList();
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id : " + id));

        if (!category.getName().equals(request.getName())
                && categoryRepository.existsByName(request.getName())) {

            throw new DuplicateCategoryException(
                    "Category already exists with name : " + request.getName());

        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(updatedCategory.getId())
                .name(updatedCategory.getName())
                .description(updatedCategory.getDescription())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id : " + id));

        categoryRepository.delete(category);

    }

}