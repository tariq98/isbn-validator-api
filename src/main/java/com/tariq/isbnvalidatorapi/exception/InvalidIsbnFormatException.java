package com.tariq.isbnvalidatorapi.exception;

public class InvalidIsbnFormatException extends RuntimeException {
    public InvalidIsbnFormatException(String message) {
        super(message);
    }
}
