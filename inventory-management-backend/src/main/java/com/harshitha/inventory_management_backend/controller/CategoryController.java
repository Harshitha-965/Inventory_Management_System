package com.harshitha.inventory_management_backend.controller;

import com.harshitha.inventory_management_backend.dto.request.CategoryRequest;
import com.harshitha.inventory_management_backend.dto.response.CategoryResponse;
import com.harshitha.inventory_management_backend.service.CategoryService;
import com.harshitha.inventory_management_backend.util.ApiResponse;
import com.harshitha.inventory_management_backend.util.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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

}