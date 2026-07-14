package com.harshitha.inventory_management_backend.exception;

public class DuplicateProductException extends RuntimeException {

    public DuplicateProductException(String message) {
        super(message);
    }

}