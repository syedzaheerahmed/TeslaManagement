package com.example.TeslaManagement.CustomException;

public class AccountDisabledException extends RuntimeException {
    public AccountDisabledException(String message) {
        super(message);
    }
}