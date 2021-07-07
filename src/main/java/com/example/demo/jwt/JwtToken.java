package com.example.demo.jwt;

import lombok.Data;

@Data
public class JwtToken {
    private String token;

    private Throwable throwable;
}
