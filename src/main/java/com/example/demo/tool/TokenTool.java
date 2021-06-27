package com.example.demo.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.User;
public class TokenTool {
    public String getToken(User user) {
        String token = "";
        token = JWT.create()
                // withAudience()存入需要保存在 token 的信息，这里把用户 ID 存入 token 中
                .withAudience(user.getId().toString())
                // Algorithm.HMAC256():使用 HS256 生成 token,密钥保存在服务端。
                .sign(Algorithm.HMAC256("jwt_secret"));
        return token;
    }
}
