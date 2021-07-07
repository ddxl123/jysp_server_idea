package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.User;
import com.example.demo.jwt.exception.JwtTokenExpiredException;
import com.example.demo.jwt.exception.JwtVerifyException;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Data
public class Jwtter {

    /**
     * JwtClaims 对应的字段名
     */
    private final String jwtClaimKeyUserId = "userId";

    /**
     * 只有被 verifyToken 成功后才能获取。
     */
    private JwtClaims jwtClaims = new JwtClaims();

    /**
     * 只有被 generateToken 成功后才能获取。
     */
    private JwtToken jwtToken = new JwtToken();

    /**
     * 验证 token。
     * 若验证成功，则无操作。
     * 若验证失败，则抛出异常。
     *
     * @throws JwtTokenExpiredException token 过期
     * @throws JwtVerifyException       验证异常
     */
    public void verifyToken(String token) throws JwtTokenExpiredException, JwtVerifyException {
        try {
            DecodedJWT decodedJWT = JWT
                    .require(Algorithm.HMAC256(JwtConstants.JWT_SECRET))
                    .build()
                    .verify(token);
            // 获取 claims。
            jwtClaims.setUserId(decodedJWT.getClaim(jwtClaimKeyUserId).asString());
        } catch (Throwable throwable) {
            if (throwable.getClass() == TokenExpiredException.class) {
                throw new JwtTokenExpiredException(throwable);
            } else {
                throw new JwtVerifyException(throwable);
            }
        }
    }

    /**
     * 生成 token
     */
    public boolean generateToken(User user) {
        try {
            jwtToken.setToken(JWT.create()
                    .withAudience(user.getId().toString()) // 签发对象
                    .withIssuedAt(Date.from(Instant.now())) // 发行时间
                    .withExpiresAt(Date.from(Instant.now().plus(Duration.ofDays(JwtConstants.TOKEN_EXPIRE)))) // 过期时间
                    .withClaim(jwtClaimKeyUserId, user.getId().toString()) // 载荷，必须与 JwtClaims 类对应
                    .sign(Algorithm.HMAC256(JwtConstants.JWT_SECRET))
            );
            return true;
        } catch (Throwable throwable) {
            jwtToken.setThrowable(throwable);
            return false;
        }
    }
}
