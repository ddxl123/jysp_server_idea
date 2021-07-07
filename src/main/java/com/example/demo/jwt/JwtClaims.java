package com.example.demo.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class JwtClaims {
    @NonNull
    private String userId;
}
