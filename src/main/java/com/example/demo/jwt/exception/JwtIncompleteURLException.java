package com.example.demo.jwt.exception;

public class JwtIncompleteURLException extends BaseJwtException {
    public JwtIncompleteURLException(String message) {
        super("Incomplete URL. Current URL: " + message);
    }
}
