package com.example.demo.controller;

import com.example.demo.entityvo.responsevo.ResponseVO;
import com.example.demo.entityvo.responsevo.ResponseWithLog;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Validated 的异常处理。
     * 响应码只能放在 controller 中进行标记，其他情况全部报异常，让客户端处理。
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseVO errorHandler(BindException bindException) {
        ResponseVO responseVO = new ResponseVO();
        try {
            String errMessage;
            if (bindException.hasErrors()) {
                errMessage = bindException.getAllErrors().get(0).getDefaultMessage();
            } else {
                // 可能抛出 —— 不存在异常，但是就是被抛出
                return setUnknownError(responseVO, 5001, bindException);
            }

            if (!StringUtils.hasText(errMessage)) {
                // 可能抛出 —— errMessage 为空
                return setUnknownError(responseVO, 5002, bindException);
            }

            StringTokenizer stringTokenizer = new StringTokenizer(errMessage, ",");

            // 设置 code
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    // 可能抛出 —— 若 nextToken 为 null 或 "", 或非 int 类型, 则会抛异常。
                    responseVO.setCode(Integer.parseInt(stringTokenizer.nextToken()));
                } catch (NumberFormatException numberFormatException) {
                    return setUnknownError(responseVO, 5003, numberFormatException);
                }
            }

            // 设置 message
            StringBuilder stringBuffer = new StringBuilder();
            while (stringTokenizer.hasMoreTokens()) {
                stringBuffer.append(stringTokenizer.nextToken());
            }
            responseVO.setMessage(stringBuffer.toString());

            // 响应已知的 Verify error
            return responseVO;
        } catch (Throwable throwable) {
            return setUnknownError(responseVO, 5004, throwable);
        }
    }

    /**
     * 拦截其他未知 error
     */
    @ExceptionHandler(Throwable.class)
    public ResponseVO otherError(Throwable throwable) {
        ResponseVO responseVO = new ResponseVO();
        return setUnknownError(responseVO, 5005, throwable);
    }

    /**
     * 设置未知错误。
     */
    ResponseVO setUnknownError(ResponseVO responseVO, int code, Throwable throwable) {
        responseVO
                .setCode(code)
                .setData(null)
                .setMessage("错误(" + code + "), 请咨询管理员")
                .responseWithLog(new ResponseWithLog(logger, true, throwable));
        return responseVO;
    }

}
