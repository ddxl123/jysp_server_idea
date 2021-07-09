package com.example.demo.controller;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.example.demo.constant.code.Code3;
import com.example.demo.controller.responsevo.ResponseVO;
import com.example.demo.exception.InterceptorExceptionPackage;
import com.example.demo.exception.JwtExceptionPackage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringTokenizer;

/**
 * 类响应码 —— 3000000
 *
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
    public ResponseVO<Void> otherExceptionHandler(Throwable throwable) {
        return setResponseException(Code3.C1_01_01, "未知异常", throwable);
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
                return setResponseException(Code3.C1_02_01, "不存在 bindException 的 error。", bindException);
            }

            if (!StringUtils.hasText(errMessage)) {
                return setResponseException(Code3.C1_02_02, "存在 bindException 的 error，但为指定 message。", bindException);
            }

            // 用 "," 将 errMessage 分割成 code 和 message
            StringTokenizer stringTokenizer = new StringTokenizer(errMessage, ",");

            ResponseVO<Void> responseVO = new ResponseVO<>();

            if (stringTokenizer.hasMoreTokens()) {
                try {
                    responseVO.setCode(Integer.parseInt(stringTokenizer.nextToken()));
                } catch (NumberFormatException numberFormatException) {
                    return setResponseException(Code3.C1_02_03, "可能是 Message 格式异常，code 的 String 类型无法解析成 int 类型", numberFormatException);
                }
            }

            // 设置 message
            StringBuilder stringBuffer = new StringBuilder();
            while (stringTokenizer.hasMoreTokens()) {
                stringBuffer.append(stringTokenizer.nextToken());
            }
            responseVO.setMessage(stringBuffer.toString());

            // 验证结果不匹配的处理。
            // 这里不需要输出日志。
            return responseVO;
        } catch (Throwable throwable) {
            return setResponseException(Code3.C1_02_04, "请求参数的验证，出现未知异常！", throwable);
        }
    }

    /**
     * MybatisPlus 的 service、mapper 等对数据库的操作出现的异常。
     */
    @ExceptionHandler(MybatisPlusException.class)
    public ResponseVO<Void> mybatisPlusExceptionHandler(MybatisPlusException mybatisPlusException) {
        return setResponseException(Code3.C1_03_01, "MybatisPlus 的 service、mapper 等对数据库的操作出现的异常。", mybatisPlusException);
    }


    /**
     * 邮箱发送异常。
     */
    @ExceptionHandler(MailException.class)
    public ResponseVO<Void> mailExceptionHandler(MailException mailException) {
        return setResponseException(Code3.C1_04_01, "邮箱发送异常", mailException);
    }

    /**
     * jwt 验证异常。包含 token 验证异常、token 过期处理。
     * <p>
     * 不包含 token 创建异常
     */
    @ExceptionHandler(JwtExceptionPackage.class)
    public ResponseVO<Void> baseJwtException(JwtExceptionPackage jwtExceptionPackage) {
        if (jwtExceptionPackage.getExpiredException() != null) {
            return new ResponseVO<Void>()
                    .setCode(Code3.C1_05_01)
                    .setMessage("用户过期，请重新登陆！")
                    .setData(null);
        }
        //
        else if (jwtExceptionPackage.getDecodeException() != null) {
            return setResponseException(Code3.C1_05_02, "Token 验证不通过！用户发出了错误 Token ，可能存在应用数据被篡改的操作！", jwtExceptionPackage.getDecodeException());
        }
        //
        else if (jwtExceptionPackage.getOtherException() != null) {
            return setResponseException(Code3.C1_05_03, "Token 验证异常！", jwtExceptionPackage.getOtherException());
        }
        //
        else {
            return setResponseException(Code3.C1_05_04, "JwtExceptionPackage 实例中含有未判断的 Exception！", jwtExceptionPackage.getExistVerifyException());
        }
    }

    /**
     * 拦截器的异常。
     */
    @ExceptionHandler(InterceptorExceptionPackage.class)
    public ResponseVO<Void> baseInterceptorException(InterceptorExceptionPackage interceptorExceptionPackage) {
        if (interceptorExceptionPackage.getIncompleteURLException() != null) {
            return setResponseException(Code3.C1_06_01, "URL 不符合规范！", interceptorExceptionPackage.getIncompleteURLException());
        }
        //
        else {
            return setResponseException(Code3.C1_06_02, "InterceptorExceptionPackage 实例中含有未判断的 Exception！", interceptorExceptionPackage.getExistException());
        }
    }

    /**
     * sql 异常
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseVO<Void> sqlException(DataAccessException dataAccessException) {
        return setResponseException(Code3.C1_07_01, "数据入口异常，可能是 实体 与 数据表 不相互对应！", dataAccessException);
    }

}
