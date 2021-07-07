package com.example.demo.controller.responsevo;

import com.example.demo.util.logger.Logout;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author 10338
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseVO<T> {
    /**
     * 响应码。
     */
    @NonNull
    @Getter
    private Integer code;

    /**
     * 响应消息。无论是成功还是失败。
     */
    @Nullable
    @Getter
    private String message;

    /**
     * 响应数据。必须
     */
    @Nullable
    @Getter
    private T data;

    public ResponseVO<T> setCode(@NonNull Integer code) {
        this.code = code;
        return this;
    }

    public ResponseVO<T> setMessage(@NonNull String message) {
        this.message = message;
        return this;
    }

    public ResponseVO<T> setData(@Nullable T data) {
        this.data = data;
        return this;
    }

    /**
     * 是否在响应的同时输出日志。
     */
    public ResponseVO<T> outputLog(@NonNull String description, @Nullable Throwable throwable, @NonNull Logger logger, @NonNull Integer loggerLevel) {
        new Logout(description, this, throwable, logger, loggerLevel).output();
        return this;
    }
}
