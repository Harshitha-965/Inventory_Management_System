package com.harshitha.inventory_management_backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harshitha.inventory_management_backend.repository.ProductRepository;
import com.harshitha.inventory_management_backend.repository.PurchaseOrderRepository;
import com.harshitha.inventory_management_backend.repository.SupplierRepository;
import com.harshitha.inventory_management_backend.service.PurchaseOrderService;

import java.util.ArrayList;

import com.harshitha.inventory_management_backend.dto.request.PurchaseOrderItemRequest;
import com.harshitha.inventory_management_backend.dto.request.PurchaseOrderRequest;
import com.harshitha.inventory_management_backend.dto.response.PurchaseOrderItemResponse;
import com.harshitha.inventory_management_backend.dto.response.PurchaseOrderResponse;
import com.harshitha.inventory_management_backend.entity.Product;
import com.harshitha.inventory_management_backend.entity.PurchaseOrder;
import com.harshitha.inventory_management_backend.entity.PurchaseOrderItem;
import com.harshitha.inventory_management_backend.entity.Supplier;
import com.harshitha.inventory_management_backend.exception.ProductNotFoundException;
import com.harshitha.inventory_management_backend.exception.PurchaseOrderNotFoundException;
import com.harshitha.inventory_management_backend.exception.SupplierNotFoundException;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public PurchaseOrderServiceImpl(
            PurchaseOrderRepository purchaseOrderRepository,
            ProductRepository productRepository,
            SupplierRepository supplierRepository) {

        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id : " + request.getSupplierId()));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .orderDate(request.getOrderDate())
                .status(request.getStatus())
                .supplier(supplier)
                .build();

        List<PurchaseOrderItem> orderItems = new ArrayList<>();

        for (PurchaseOrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new ProductNotFoundException(
                                    "Product not found with id : "
                                            + itemRequest.getProductId()));

            PurchaseOrderItem item = PurchaseOrderItem.builder()
                    .purchaseOrder(purchaseOrder)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .unitPrice(product.getPrice())
                    .totalPrice(product.getPrice()
                            .multiply(java.math.BigDecimal.valueOf(itemRequest.getQuantity())))
                    .build();
            
            product.setQuantity(product.getQuantity() + itemRequest.getQuantity());
            productRepository.save(product);
            orderItems.add(item);
        }
        purchaseOrder.setItems(orderItems);
        PurchaseOrder savedOrder = purchaseOrderRepository.save(purchaseOrder);

        List<PurchaseOrderItemResponse> itemResponses =
            savedOrder.getItems().stream()
                    .map(item -> PurchaseOrderItemResponse.builder()
                            .id(item.getId())
                            .productId(item.getProduct().getId())
                            .productName(item.getProduct().getName())
                            .quantity(item.getQuantity())
                            .unitPrice(item.getUnitPrice())
                            .totalPrice(item.getTotalPrice())
                            .build())
                    .toList();
        
        return PurchaseOrderResponse.builder()
            .id(savedOrder.getId())
            .orderDate(savedOrder.getOrderDate())
            .status(savedOrder.getStatus())
            .supplierId(savedOrder.getSupplier().getId())
            .supplierName(savedOrder.getSupplier().getName())
            .items(itemResponses)
            .build();
    }

    @Override
    public PurchaseOrderResponse getPurchaseOrderById(Long id) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new PurchaseOrderNotFoundException(
                                "Purchase Order not found with id : " + id));

        List<PurchaseOrderItemResponse> itemResponses =
                purchaseOrder.getItems().stream()
                        .map(item -> PurchaseOrderItemResponse.builder()
                                .id(item.getId())
                                .productId(item.getProduct().getId())
                                .productName(item.getProduct().getName())
                                .quantity(item.getQuantity())
                                .unitPrice(item.getUnitPrice())
                                .totalPrice(item.getTotalPrice())
                                .build())
                        .toList();

        return PurchaseOrderResponse.builder()
                .id(purchaseOrder.getId())
                .orderDate(purchaseOrder.getOrderDate())
                .status(purchaseOrder.getStatus())
                .supplierId(purchaseOrder.getSupplier().getId())
                .supplierName(purchaseOrder.getSupplier().getName())
                .items(itemResponses)
                .build();
    }

    @Override
    public List<PurchaseOrderResponse> getAllPurchaseOrders() {

        List<PurchaseOrder> orders = purchaseOrderRepository.findAll();

        return orders.stream()
                .map(order -> {

                    List<PurchaseOrderItemResponse> itemResponses =
                            order.getItems().stream()
                                    .map(item -> PurchaseOrderItemResponse.builder()
                                            .id(item.getId())
                                            .productId(item.getProduct().getId())
                                            .productName(item.getProduct().getName())
                                            .quantity(item.getQuantity())
                                            .unitPrice(item.getUnitPrice())
                                            .totalPrice(item.getTotalPrice())
                                            .build())
                                    .toList();

                    return PurchaseOrderResponse.builder()
                            .id(order.getId())
                            .orderDate(order.getOrderDate())
                            .status(order.getStatus())
                            .supplierId(order.getSupplier().getId())
                            .supplierName(order.getSupplier().getName())
                            .items(itemResponses)
                            .build();
                })
                .toList();
    }

    @Override
    public void deletePurchaseOrder(Long id) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new PurchaseOrderNotFoundException(
                                "Purchase Order not found with id : " + id));

        purchaseOrderRepository.delete(purchaseOrder);
    }

}