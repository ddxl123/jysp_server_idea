package com.example.demo.jwt.exception;

public class JwtVerifyException extends BaseJwtException {
    public JwtVerifyException(String message) {
        super(message);
    }

    public JwtVerifyException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public JwtVerifyException(Throwable throwable) {
        super(throwable);
    }
}
