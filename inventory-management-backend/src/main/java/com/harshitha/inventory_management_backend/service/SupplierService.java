package com.harshitha.inventory_management_backend.service;

import java.util.List;

import com.harshitha.inventory_management_backend.dto.request.SupplierRequest;
import com.harshitha.inventory_management_backend.dto.response.SupplierResponse;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse getSupplierById(Long id);

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse updateSupplier(Long id, SupplierRequest request);

    void deleteSupplier(Long id);

}