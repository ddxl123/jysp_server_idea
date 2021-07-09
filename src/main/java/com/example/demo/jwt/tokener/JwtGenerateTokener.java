package com.example.demo.jwt.tokener;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Accessors(chain = true)
public class JwtGenerateTokener extends BaseTokener {
    @Nullable
    @Setter
    @Getter
    String token;
}
