package com.example.demo.exception;

public class BaseExceptionPackage extends Exception {
    BaseExceptionPackage() {
        super("获取 throwable 信息不应该从 BaseExceptionPackage 的子类自身中获取，而应该从其子类自身的一系列 Exception 字段获取！");
    }

    public BaseExceptionPackage(String message) {
        super(message);
    }

    public BaseExceptionPackage(Throwable throwable) {
        super(throwable);
    }

    protected String unknownMessage() {
        return this.getClass().toString() + " 实例中需要的 Exception 成员全为 null！";
    }

}
