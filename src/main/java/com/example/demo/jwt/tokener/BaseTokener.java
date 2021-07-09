package com.example.demo.jwt.tokener;

import com.example.demo.jwt.JwtClaims;
import com.example.demo.exception.JwtExceptionPackage;
import lombok.Getter;

public class BaseTokener {
    @Getter
    JwtClaims jwtClaims = new JwtClaims();

    @Getter
    JwtExceptionPackage jwtExceptionPackage = new JwtExceptionPackage();
}
