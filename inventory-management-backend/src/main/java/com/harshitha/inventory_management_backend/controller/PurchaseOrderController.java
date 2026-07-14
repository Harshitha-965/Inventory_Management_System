package com.harshitha.inventory_management_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.harshitha.inventory_management_backend.dto.request.PurchaseOrderRequest;
import com.harshitha.inventory_management_backend.dto.response.PurchaseOrderResponse;
import com.harshitha.inventory_management_backend.service.PurchaseOrderService;
import com.harshitha.inventory_management_backend.util.ApiResponse;
import com.harshitha.inventory_management_backend.util.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<ApiResponse<PurchaseOrderResponse>> createPurchaseOrder(
            @Valid @RequestBody PurchaseOrderRequest request) {

        return ResponseBuilder.success(
                purchaseOrderService.createPurchaseOrder(request),
                "Purchase Order created successfully",
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PurchaseOrderResponse>> getPurchaseOrderById(
            @PathVariable Long id) {

        return ResponseBuilder.success(
                purchaseOrderService.getPurchaseOrderById(id),
                "Purchase Order retrieved successfully",
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PurchaseOrderResponse>>> getAllPurchaseOrders() {

        return ResponseBuilder.success(
                purchaseOrderService.getAllPurchaseOrders(),
                "Purchase Orders retrieved successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePurchaseOrder(
            @PathVariable Long id) {

        purchaseOrderService.deletePurchaseOrder(id);

        return ResponseBuilder.success(
                null,
                "Purchase Order deleted successfully",
                HttpStatus.OK);
    }
}