package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.constant.JwtConstant;
import com.example.demo.entity.User;
import com.example.demo.exception.JwtExceptionPackage;
import com.example.demo.jwt.tokener.JwtGenerateTokener;
import com.example.demo.jwt.tokener.JwtVerifyTokener;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Data
public class Jwtter {

    /**
     * 验证 token。
     */
    public JwtVerifyTokener verifyToken(@Nullable String token) {
        JwtVerifyTokener jwtVerifyTokener = new JwtVerifyTokener();
        try {
            if (token == null) {
                token = "";
            }
            DecodedJWT decodedJWT = JWT
                    .require(Algorithm.HMAC256(JwtConstant.JWT_SECRET))
                    .build()
                    .verify(token);
            // 验证成功，写入 claims
            jwtVerifyTokener.getJwtClaims().setUserIdValue(decodedJWT.getClaim(jwtVerifyTokener.getJwtClaims().userIdKey).asString());
        } catch (Throwable throwable) {
            if (throwable.getClass() == TokenExpiredException.class) {
                jwtVerifyTokener.getJwtExceptionPackage().setExpiredException(new JwtExceptionPackage(throwable));
            } else if (throwable.getClass() == JWTDecodeException.class) {
                jwtVerifyTokener.getJwtExceptionPackage().setDecodeException(new JwtExceptionPackage(throwable));
            } else {
                jwtVerifyTokener.getJwtExceptionPackage().setOtherException(new JwtExceptionPackage(throwable));
            }
        }
        return jwtVerifyTokener;
    }

    /**
     * 生成 token
     */
    public JwtGenerateTokener generateToken(User user) {
        JwtGenerateTokener jwtGenerateTokener = new JwtGenerateTokener();
        try {
            jwtGenerateTokener.getJwtClaims()
                    .setUserIdValue(user.getId().toString());
            jwtGenerateTokener.setToken(
                    JWT.create()
                            .withAudience(user.getId().toString()) // 签发对象
                            .withIssuedAt(Date.from(Instant.now())) // 发行时间
                            .withExpiresAt(Date.from(Instant.now().plus(Duration.ofDays(JwtConstant.TOKEN_EXPIRE)))) // 过期时间
                            .withClaim(
                                    jwtGenerateTokener.getJwtClaims().userIdKey,
                                    jwtGenerateTokener.getJwtClaims().getUserIdValue()) // 载荷，必须与 JwtClaims 类对应
                            .sign(Algorithm.HMAC256(JwtConstant.JWT_SECRET))
            );
        } catch (Throwable throwable) {
            jwtGenerateTokener.getJwtExceptionPackage().setGenerateTokenException(new JwtExceptionPackage(throwable));
        }
        return jwtGenerateTokener;
    }
}
