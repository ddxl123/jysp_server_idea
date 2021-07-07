package com.example.demo.jwt;

import com.example.demo.jwt.exception.BaseJwtException;
import com.example.demo.jwt.exception.JwtIncompleteURLException;
import com.example.demo.util.logger.Logout;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    final
    Logger logger;

    public JwtInterceptor(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BaseJwtException {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            new Logout(
                    "jwt 拦截器：" + "\n" +
                            "传入的 handler 对象为：" + handler.getClass().toString() + "\n" +
                            "URL: " + request.getRequestURL(),
                    null,
                    null,
                    logger,
                    0
            ).output();
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Pattern pattern = Pattern.compile("/\\w+");
        Matcher matcher = pattern.matcher(request.getRequestURL());

        // 获取到 http://xxx/yyy/zzz 中的 "/yyy"
        if (matcher.find() && matcher.find()) {
            String group = matcher.group();

            // 需要直接通过的 URL
            if (group.contains("swagger") || group.contains("v3") || group.contains("error")) {
                new Logout("未被拦截的 URL：" + request.getRequestURL(), null, null, logger, 0).output();
                return true;
            }

            // 若 "/yyy" 为不需要 jwt 认证
            if (group.equals(JwtConstants.NO_JWT_URL)) {
                return true;
            }
            // 若 "/yyy" 为需要 jwt 认证
            else if (group.equals(JwtConstants.JWT_URL)) {
                String token = request.getHeader(JwtConstants.TOKEN_HEADER_KEY);// 获取请求头中的 token
                Jwtter jwtter = new Jwtter();
                jwtter.verifyToken(token); // 验证 token
                return true;
            }

            // 其他
            else {
                throw new JwtIncompleteURLException(request.getRequestURL().toString());
            }

        } else {
            throw new JwtIncompleteURLException(request.getRequestURL().toString());
        }
    }
}
