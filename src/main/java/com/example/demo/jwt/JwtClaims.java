package com.example.demo.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;

public class JwtClaims {

    public final String userIdKey = "user_id";

    @NonNull
    @Getter
    @Setter
    @Accessors(chain = true)
    private String userIdValue;
}
