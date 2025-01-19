package com.shivraj.CabBookingSystem.exceptions;

public class CabNotFoundException extends RuntimeException {
    public CabNotFoundException(String message) {
        super(message);
    }
}
