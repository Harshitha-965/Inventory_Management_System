package com.harshitha.inventory_management_backend.exception;

public class SupplierNotFoundException extends RuntimeException {

    public SupplierNotFoundException(String message) {
        super(message);
    }

}