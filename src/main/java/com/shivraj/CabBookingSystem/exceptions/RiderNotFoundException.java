package com.shivraj.CabBookingSystem.exceptions;

public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException(String message) {
        super(message);
    }
}
