package com.harshitha.inventory_management_backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harshitha.inventory_management_backend.dto.request.SupplierRequest;
import com.harshitha.inventory_management_backend.dto.response.SupplierResponse;
import com.harshitha.inventory_management_backend.entity.Supplier;
import com.harshitha.inventory_management_backend.exception.DuplicateSupplierException;
import com.harshitha.inventory_management_backend.exception.SupplierNotFoundException;
import com.harshitha.inventory_management_backend.repository.SupplierRepository;
import com.harshitha.inventory_management_backend.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
	@Override
	public SupplierResponse createSupplier(SupplierRequest request) {
		if (supplierRepository.existsByName(request.getName())) {
            throw new DuplicateSupplierException(
                    "Supplier already exists with name : " + request.getName());
        }

        if (supplierRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateSupplierException(
                    "Supplier already exists with email : " + request.getEmail());
        }

        if (supplierRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateSupplierException(
                    "Supplier already exists with phone : " + request.getPhone());
        }
        Supplier supplier = Supplier.builder()
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();

        Supplier savedSupplier = supplierRepository.save(supplier);

        return SupplierResponse.builder()
                .id(savedSupplier.getId())
                .name(savedSupplier.getName())
                .email(savedSupplier.getEmail())
                .phone(savedSupplier.getPhone())
                .address(savedSupplier.getAddress())
                .build();
    }

	@Override
    public SupplierResponse getSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id : " + id));

        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .build();
    }

	@Override
    public List<SupplierResponse> getAllSuppliers() {

        List<Supplier> suppliers = supplierRepository.findAll();

        return suppliers.stream()
                .map(supplier -> SupplierResponse.builder()
                        .id(supplier.getId())
                        .name(supplier.getName())
                        .email(supplier.getEmail())
                        .phone(supplier.getPhone())
                        .address(supplier.getAddress())
                        .build())
                .toList();
    }

	@Override
    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id : " + id));

        if (!supplier.getName().equals(request.getName())
                && supplierRepository.existsByName(request.getName())) {

            throw new DuplicateSupplierException(
                    "Supplier already exists with name : " + request.getName());
        }

        if (!supplier.getEmail().equals(request.getEmail())
                && supplierRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateSupplierException(
                    "Supplier already exists with email : " + request.getEmail());
        }

        if (!supplier.getPhone().equals(request.getPhone())
                && supplierRepository.existsByPhone(request.getPhone())) {

            throw new DuplicateSupplierException(
                    "Supplier already exists with phone : " + request.getPhone());
        }

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return SupplierResponse.builder()
                .id(updatedSupplier.getId())
                .name(updatedSupplier.getName())
                .email(updatedSupplier.getEmail())
                .phone(updatedSupplier.getPhone())
                .address(updatedSupplier.getAddress())
                .build();
    }

	@Override
    public void deleteSupplier(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id : " + id));

        supplierRepository.delete(supplier);
    }

}
