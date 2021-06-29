package com.example.demo.entityvo.responsevo;

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
public class ResponseVO {
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
    private Object data;

    public ResponseVO setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseVO setData(@Nullable Object data) {
        this.data = data;
        return this;
    }

    public ResponseVO setMessage(@Nullable String message) {
        this.message = message;
        return this;
    }

    /**
     * 必须放在最后，因为要 output
     */
    public ResponseVO responseWithLog(@NotNull ResponseWithLog responseWithLog) {
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
