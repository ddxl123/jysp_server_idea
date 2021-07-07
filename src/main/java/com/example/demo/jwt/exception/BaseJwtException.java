package com.example.demo.jwt.exception;

public class BaseJwtException extends Exception {
    public BaseJwtException() {
        super();
    }

    public BaseJwtException(String message) {
        super(message);
    }

    public BaseJwtException(Throwable throwable) {
        super(throwable);
    }

    public BaseJwtException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
