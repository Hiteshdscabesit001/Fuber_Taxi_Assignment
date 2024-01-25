package com.taxi.exception;

public class NoAvailableTaxisException extends RuntimeException {
    public NoAvailableTaxisException(String message) {
        super(message);
    }
}
