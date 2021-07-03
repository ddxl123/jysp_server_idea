package com.example.demo.controller;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.example.demo.controller.responsevo.ResponseVO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringTokenizer;


/**
 * @author 10338
 */
@RestControllerAdvice
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ExceptionControllerAdvice {

    Logger logger;

    /**
     * 设置未知错误。
     */
    ResponseVO<Void> setUnknownException(int code, Throwable throwable) {
        ResponseVO<Void> responseVO = new ResponseVO<>();
        responseVO
                .setCode(code)
                .setData(null)
                .setMessage("错误(" + code + "), 请咨询管理员")
                .outputLog("未知错误。", throwable, logger, 2);
        return responseVO;
    }

    /**
     * 拦截其他未知 error
     */
    @ExceptionHandler(Throwable.class)
    public ResponseVO<Void> otherExceptionHandler(Throwable throwable) {
        return setUnknownException(10000, throwable);
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
                // 验证发生异常的处理。 —— 不存在异常，但是就是被抛出
                return setUnknownException(10001, bindException);
            }

            if (!StringUtils.hasText(errMessage)) {
                // 验证发生异常的处理。 —— errMessage 为空
                return setUnknownException(10002, bindException);
            }

            StringTokenizer stringTokenizer = new StringTokenizer(errMessage, ",");

            ResponseVO<Void> responseVO = new ResponseVO<>();

            // 设置 code
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    responseVO.setCode(Integer.parseInt(stringTokenizer.nextToken()));
                } catch (NumberFormatException numberFormatException) {
                    // 验证发生异常的处理。 —— 若 nextToken 为 null 或 "", 或非 int 类型, 则会抛异常。
                    return setUnknownException(10003, numberFormatException);
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
            // 验证发生异常的处理。
            return setUnknownException(10004, throwable);
        }
    }

    /**
     * MybatisPlus 的 service、mapper 等对数据库的操作抛出的异常。
     */
    @ExceptionHandler(MybatisPlusException.class)
    public ResponseVO<Void> mybatisPlusExceptionHandler(MybatisPlusException mybatisPlusException) {
        return setUnknownException(10005, mybatisPlusException);
    }


    /**
     * 邮箱发送异常。
     */
    @ExceptionHandler(MailException.class)
    public ResponseVO<Void> mailExceptionHandler(MailException mailException) {
        return setUnknownException(10006, mailException);
    }


}
