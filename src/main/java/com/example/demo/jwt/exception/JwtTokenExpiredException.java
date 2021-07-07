package com.example.demo.jwt.exception;

public class JwtTokenExpiredException extends BaseJwtException {
    public JwtTokenExpiredException(Throwable throwable) {
        super(throwable);
    }
}
