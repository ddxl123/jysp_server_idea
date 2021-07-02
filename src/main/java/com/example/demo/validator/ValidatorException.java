package com.example.demo.validator;

/**
 * @author 10338
 */
public class ValidatorException extends Exception {
    public ValidatorException() {
        super("请求参数验证-内部验证器异常");
    }
}
