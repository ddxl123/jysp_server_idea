package com.example.demo.controller;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.example.demo.controller.responsevo.ResponseVO;
import com.example.demo.jwt.exception.BaseJwtException;
import com.example.demo.jwt.exception.JwtTokenCreateException;
import com.example.demo.jwt.exception.JwtTokenExpiredException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringTokenizer;


/**
 * 类响应码 —— 3000000
 * @author 10338
 */
@RestControllerAdvice
@AllArgsConstructor
public class ExceptionControllerAdvice {

    Logger logger;

    /**
     * 设置未知错误。
     */
    ResponseVO<Void> setResponseException(@NonNull int code, @NonNull String description, @NonNull Throwable throwable) {
        return new ResponseVO<Void>()
                .setCode(code)
                .setData(null)
                .setMessage("错误(" + code + "), 请咨询管理员")
                .outputLog("异常拦截：" + description, throwable, logger, 2);
    }

    /**
     * 拦截其他未知异常。
     */
    @ExceptionHandler(Throwable.class)
    @RequestMapping(
            path = "/login_and_register_by_email",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseVO<Void> otherExceptionHandler(Throwable throwable) {
        return setResponseException(10000, "未知异常", throwable);
    }

    /**
     * Validated 失败的处理。
     * <br>
     * 其中包含：
     * 1. 验证结果不匹配的处理。
     * 2. 验证发生异常的处理。
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseVO<Void> bindExceptionHandler(BindException bindException) {
        try {
            String errMessage;
            if (bindException.hasErrors()) {
                errMessage = bindException.getAllErrors().get(0).getDefaultMessage();
            } else {
                return setResponseException(10001, "不存在 bindException 的 error。", bindException);
            }

            if (!StringUtils.hasText(errMessage)) {
                return setResponseException(10002, "存在 bindException 的 error，但为指定 message。", bindException);
            }

            // 用 "," 将 errMessage 分割成 code 和 message
            StringTokenizer stringTokenizer = new StringTokenizer(errMessage, ",");

            ResponseVO<Void> responseVO = new ResponseVO<>();

            if (stringTokenizer.hasMoreTokens()) {
                try {
                    responseVO.setCode(Integer.parseInt(stringTokenizer.nextToken()));
                } catch (NumberFormatException numberFormatException) {
                    return setResponseException(10003, "可能是 Message 格式异常，code 的 String 类型无法解析成 int 类型", numberFormatException);
                }
            }

            // 设置 message
            StringBuilder stringBuffer = new StringBuilder();
            while (stringTokenizer.hasMoreTokens()) {
                stringBuffer.append(stringTokenizer.nextToken());
            }
            responseVO.setMessage(stringBuffer.toString());

            // 验证结果不匹配的处理。
            return responseVO;
        } catch (Throwable throwable) {
            return setResponseException(10004, "请求参数的验证，出现未知异常！", throwable);
        }
    }

    /**
     * MybatisPlus 的 service、mapper 等对数据库的操作出现的异常。
     */
    @ExceptionHandler(MybatisPlusException.class)
    public ResponseVO<Void> mybatisPlusExceptionHandler(MybatisPlusException mybatisPlusException) {
        return setResponseException(10005, "MybatisPlus 的 service、mapper 等对数据库的操作出现的异常。", mybatisPlusException);
    }


    /**
     * 邮箱发送异常。
     */
    @ExceptionHandler(MailException.class)
    public ResponseVO<Void> mailExceptionHandler(MailException mailException) {
        return setResponseException(10006, "邮箱发送异常", mailException);
    }

    /**
     * jwt 验证异常。包含 token 过期处理、token 创建异常。
     */
    @ExceptionHandler(BaseJwtException.class)
    public ResponseVO<Void> jwtException(BaseJwtException baseJwtException) {
        if (baseJwtException.getClass() == JwtTokenExpiredException.class) {
            return new ResponseVO<Void>()
                    .setCode(10007)
                    .setMessage("用户过期，请重新登陆！")
                    .setData(null);
        } else if (baseJwtException.getClass() == JwtTokenCreateException.class) {
            return setResponseException(10008, "Token 创建异常！", baseJwtException);
        } else {
            return setResponseException(10008, "Token 验证异常！", baseJwtException);
        }
    }

}
