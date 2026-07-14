package com.harshitha.inventory_management_backend.controller;

import com.harshitha.inventory_management_backend.dto.request.SupplierRequest;
import com.harshitha.inventory_management_backend.dto.response.SupplierResponse;
import com.harshitha.inventory_management_backend.service.SupplierService;
import com.harshitha.inventory_management_backend.util.ApiResponse;
import com.harshitha.inventory_management_backend.util.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ApiResponse<SupplierResponse>> createSupplier(
            @Valid @RequestBody SupplierRequest request) {

        SupplierResponse response = supplierService.createSupplier(request);

        return ResponseBuilder.success(
                response,
                "Supplier created successfully",
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponse>> getSupplierById(
            @PathVariable Long id) {

        SupplierResponse response = supplierService.getSupplierById(id);

        return ResponseBuilder.success(
                response,
                "Supplier retrieved successfully",
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplierResponse>>> getAllSuppliers() {

        List<SupplierResponse> response = supplierService.getAllSuppliers();

        return ResponseBuilder.success(
                response,
                "Suppliers retrieved successfully",
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponse>> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequest request) {

        SupplierResponse response = supplierService.updateSupplier(id, request);

        return ResponseBuilder.success(
                response,
                "Supplier updated successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSupplier(
            @PathVariable Long id) {

        supplierService.deleteSupplier(id);

        return ResponseBuilder.success(
                null,
                "Supplier deleted successfully",
                HttpStatus.OK);
    }
}