package com.harshitha.inventory_management_backend.service;


import com.harshitha.inventory_management_backend.dto.request.ProductRequest;
import com.harshitha.inventory_management_backend.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(Long id);

    Page<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

}