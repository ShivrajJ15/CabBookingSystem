package com.shivraj.CabBookingSystem.exceptions;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(String message) {
        super(message);
    }
}
