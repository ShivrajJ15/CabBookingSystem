package com.shivraj.CabBookingSystem.exceptions;

public class CabAlreadyExistsException extends RuntimeException {
  public CabAlreadyExistsException(String message) {
    super(message);
  }
}
