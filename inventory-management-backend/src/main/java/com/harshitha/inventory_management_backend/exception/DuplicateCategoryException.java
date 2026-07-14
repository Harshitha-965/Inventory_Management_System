package com.harshitha.inventory_management_backend.exception;

public class DuplicateCategoryException extends RuntimeException {

    public DuplicateCategoryException(String message) {
        super(message);
    }

}