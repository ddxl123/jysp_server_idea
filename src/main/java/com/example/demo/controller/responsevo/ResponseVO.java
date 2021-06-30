package com.example.demo.controller.responsevo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * @author 10338
 */
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> {
    /**
     * 响应码。
     */
    @NotNull
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

    public ResponseVO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseVO<T> setData(@Nullable T data) {
        this.data = data;
        return this;
    }

    public ResponseVO<T> setMessage(@Nullable String message) {
        this.message = message;
        return this;
    }

    /**
     * 必须放在最后，因为要 output
     */
    public ResponseVO<T> outputLog(@NotNull ResponseWithLog responseWithLog) {
        responseWithLog.setResponseVO(this);
        return this;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
