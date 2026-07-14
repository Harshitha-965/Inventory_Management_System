package com.harshitha.inventory_management_backend.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.harshitha.inventory_management_backend.dto.request.ProductRequest;
import com.harshitha.inventory_management_backend.dto.response.ProductResponse;
import com.harshitha.inventory_management_backend.repository.CategoryRepository;
import com.harshitha.inventory_management_backend.repository.ProductRepository;
import com.harshitha.inventory_management_backend.repository.SupplierRepository;
import com.harshitha.inventory_management_backend.service.ProductService;

import com.harshitha.inventory_management_backend.entity.Category;
import com.harshitha.inventory_management_backend.entity.Product;
import com.harshitha.inventory_management_backend.entity.Supplier;
import com.harshitha.inventory_management_backend.exception.CategoryNotFoundException;
import com.harshitha.inventory_management_backend.exception.DuplicateProductException;
import com.harshitha.inventory_management_backend.exception.ProductNotFoundException;
import com.harshitha.inventory_management_backend.exception.SupplierNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              SupplierRepository supplierRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }
    @Override
    public ProductResponse createProduct(ProductRequest request) {

        if (productRepository.existsByName(request.getName())) {
            throw new DuplicateProductException(
                    "Product already exists with name : " + request.getName());
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id : " + request.getCategoryId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id : " + request.getSupplierId()));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(category)
                .supplier(supplier)
                .build();

        Product savedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .quantity(savedProduct.getQuantity())
                .categoryId(savedProduct.getCategory().getId())
                .categoryName(savedProduct.getCategory().getName())
                .supplierId(savedProduct.getSupplier().getId())
                .supplierName(savedProduct.getSupplier().getName())
                .build();
    }
	@Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .supplierId(product.getSupplier().getId())
                .supplierName(product.getSupplier().getName())
                .build();
    }
	@Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .supplierId(product.getSupplier().getId())
                .supplierName(product.getSupplier().getName())
                .build());

    }
		
	@Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));

        if (!product.getName().equals(request.getName())
                && productRepository.existsByName(request.getName())) {

            throw new DuplicateProductException(
                    "Product already exists with name : " + request.getName());
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id : " + request.getCategoryId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id : " + request.getSupplierId()));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(category);
        product.setSupplier(supplier);

        Product updatedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .quantity(updatedProduct.getQuantity())
                .categoryId(updatedProduct.getCategory().getId())
                .categoryName(updatedProduct.getCategory().getName())
                .supplierId(updatedProduct.getSupplier().getId())
                .supplierName(updatedProduct.getSupplier().getName())
                .build();
    }
	@Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));

        productRepository.delete(product);
    }


}