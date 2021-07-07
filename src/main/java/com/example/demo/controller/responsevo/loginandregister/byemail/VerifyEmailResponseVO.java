package com.example.demo.controller.responsevo.loginandregister.byemail;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
public class VerifyEmailResponseVO {
    private BigInteger userId;
    private String username;
    private String email;
    private Integer age;
    private String token;
}
