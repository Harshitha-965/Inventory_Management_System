package com.harshitha.inventory_management_backend.controller;

import com.harshitha.inventory_management_backend.dto.request.ProductRequest;
import com.harshitha.inventory_management_backend.dto.response.ProductResponse;
import com.harshitha.inventory_management_backend.service.ProductService;
import com.harshitha.inventory_management_backend.util.ApiResponse;
import com.harshitha.inventory_management_backend.util.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

        private final ProductService productService;

        @PostMapping
        public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
                @Valid @RequestBody ProductRequest request) {

                return ResponseBuilder.success(
                        productService.createProduct(request),
                        "Product created successfully",
                        HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
                @PathVariable Long id) {

                return ResponseBuilder.success(
                        productService.getProductById(id),
                        "Product retrieved successfully",
                        HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
                Pageable pageable) {

                return ResponseBuilder.success(
                        productService.getAllProducts(pageable),
                        "Products retrieved successfully",
                        HttpStatus.OK);

        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
                @PathVariable Long id,
                @Valid @RequestBody ProductRequest request) {

                return ResponseBuilder.success(
                        productService.updateProduct(id, request),
                        "Product updated successfully",
                        HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteProduct(
                @PathVariable Long id) {

                productService.deleteProduct(id);

                return ResponseBuilder.success(
                        null,
                        "Product deleted successfully",
                        HttpStatus.OK);
        }

        @GetMapping("/search")
        public ResponseEntity<ApiResponse<Page<ProductResponse>>> searchProducts(
                @RequestParam String name,
                Pageable pageable) {

        return ResponseBuilder.success(
                productService.searchProducts(name, pageable),
                "Products retrieved successfully",
                HttpStatus.OK);
        }

        @GetMapping("/category/{categoryId}")
        public ResponseEntity<ApiResponse<Page<ProductResponse>>> getProductsByCategory(
                @PathVariable Long categoryId,
                Pageable pageable) {

        return ResponseBuilder.success(
                productService.getProductsByCategory(categoryId, pageable),
                "Products retrieved successfully",
                HttpStatus.OK);
        }

        @GetMapping("/supplier/{supplierId}")
        public ResponseEntity<ApiResponse<Page<ProductResponse>>> getProductsBySupplier(
                @PathVariable Long supplierId,
                Pageable pageable) {

        return ResponseBuilder.success(
                productService.getProductsBySupplier(supplierId, pageable),
                "Products retrieved successfully",
                HttpStatus.OK);
        }

}