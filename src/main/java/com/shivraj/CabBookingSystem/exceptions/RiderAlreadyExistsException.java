package com.shivraj.CabBookingSystem.exceptions;

public class RiderAlreadyExistsException extends RuntimeException {
    public RiderAlreadyExistsException(String message) {
        super(message);
    }
}
