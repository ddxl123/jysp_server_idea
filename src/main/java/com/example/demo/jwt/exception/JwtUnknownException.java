package com.example.demo.jwt.exception;

public class JwtUnknownException extends BaseJwtException {
    public JwtUnknownException(Throwable throwable) {
        super(throwable);
    }
}
