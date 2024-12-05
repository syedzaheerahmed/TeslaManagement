package com.example.TeslaManagement.CustomException;

public class ReferenceNotFoundException extends RuntimeException {
    public ReferenceNotFoundException(String message) {
        super(message);
    }
}
