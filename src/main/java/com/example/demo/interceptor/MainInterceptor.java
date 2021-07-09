package com.example.demo.interceptor;

import com.example.demo.config.loggerconfig.Logger;
import com.example.demo.constant.JwtConstant;
import com.example.demo.exception.InterceptorExceptionPackage;
import com.example.demo.jwt.Jwtter;
import com.example.demo.jwt.tokener.JwtVerifyTokener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MainInterceptor implements HandlerInterceptor {

    final
    org.slf4j.Logger logger;

    public MainInterceptor(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            new Logger(
                    "Main 拦截器：" + "\n" +
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
                new Logger("未被拦截的 URL：" + request.getRequestURL(), null, null, logger, 0).output();
                return true;
            }

            // 若 "/yyy" 为不需要 jwt 认证
            if (group.equals(JwtConstant.NO_JWT_URL)) {
                return true;
            }

            // 若 "/yyy" 为需要 jwt 认证
            else if (group.equals(JwtConstant.JWT_URL)) {
                String token = request.getHeader(JwtConstant.TOKEN_HEADER_KEY);// 获取请求头中的 token

                JwtVerifyTokener jwtter = new Jwtter().verifyToken(token);// 验证 token
                if (jwtter.getJwtExceptionPackage().hasVerifyException()) {
                    throw jwtter.getJwtExceptionPackage();
                }

                return true; // 只有验证成功的才能通过，否则会抛出异常。
            }

            // 其他
            else {
                InterceptorExceptionPackage interceptorExceptionPackage = new InterceptorExceptionPackage();
                interceptorExceptionPackage.setIncompleteURLException(new InterceptorExceptionPackage("Incomplete URL. Current URL: " + request.getRequestURL().toString()));
                throw interceptorExceptionPackage;
            }

        } else {
            InterceptorExceptionPackage interceptorExceptionPackage = new InterceptorExceptionPackage();
            interceptorExceptionPackage.setIncompleteURLException(new InterceptorExceptionPackage("Incomplete URL. Current URL: " + request.getRequestURL().toString()));
            throw interceptorExceptionPackage;
        }
    }
}

