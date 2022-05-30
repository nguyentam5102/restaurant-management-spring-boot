package com.example.restaurantmanagementspringboot.exception;

public class ItemNotAvailableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ItemNotAvailableException(String msg) {
        super(msg);
    }
}
