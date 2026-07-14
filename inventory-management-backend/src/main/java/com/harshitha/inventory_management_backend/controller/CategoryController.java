package com.harshitha.inventory_management_backend.controller;

import com.harshitha.inventory_management_backend.dto.request.CategoryRequest;
import com.harshitha.inventory_management_backend.dto.response.CategoryResponse;
import com.harshitha.inventory_management_backend.service.CategoryService;
import com.harshitha.inventory_management_backend.util.ApiResponse;
import com.harshitha.inventory_management_backend.util.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {

        CategoryResponse response = categoryService.createCategory(request);

        return ResponseBuilder.success(
                response,
                "Category created successfully",
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable Long id) {

        CategoryResponse response = categoryService.getCategoryById(id);

        return ResponseBuilder.success(
                response,
                "Category retrieved successfully",
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {

        List<CategoryResponse> response = categoryService.getAllCategories();

        return ResponseBuilder.success(
                response,
                "Categories retrieved successfully",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response = categoryService.updateCategory(id, request);

        return ResponseBuilder.success(
                response,
                "Category updated successfully",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseBuilder.success(
                null,
                "Category deleted successfully",
                HttpStatus.OK
        );
    }

}