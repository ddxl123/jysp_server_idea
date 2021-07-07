package com.example.demo.jwt;

public class JwtConstants {
    /**
     * token 对应的请求头 key
     */
    public static final String TOKEN_HEADER_KEY = "token";
    /**
     * jwt 加盐的密钥
     */
    public static final String JWT_SECRET = "jwt_secret";
    /**
     * token 有效期
     */
    public static final Long TOKEN_EXPIRE = 7L;
    /**
     * 需要通过 jwt 认证的相对 URL。
     */
    public static final String JWT_URL = "/jwt";
    /**
     * 不需要通过 jwt 认证的相对 URL。
     */
    public static final String NO_JWT_URL = "/no_jwt";
}
