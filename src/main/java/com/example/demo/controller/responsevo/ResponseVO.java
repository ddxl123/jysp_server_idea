package com.example.demo.controller.responsevo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

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

    public ResponseVO<T> setCode(@NotNull Integer code) {
        this.code = code;
        return this;
    }

    public ResponseVO<T> setMessage(@NotNull String message) {
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
    public ResponseVO<T> outputLog(@NotNull String description, @Nullable Throwable throwable, @NotNull Logger logger, @NotNull Integer loggerLevel) {
        new ResponseWithLog(description, this, throwable, logger, loggerLevel).output();
        return this;
    }
}

/**
 * @author 10338
 */
@Getter
@AllArgsConstructor
class ResponseWithLog {

    /**
     * 对日志的描述。
     */
    @NotNull
    private final String description;

    /**
     * 响应给客户端的响应体。
     */
    @NotNull
    private final ResponseVO<?> responseVO;

    /**
     * 抛出的异常。
     */
    @Nullable
    private final Throwable throwable;

    /**
     * 设置 Logger，因为 Logger 没法自动注入到非配置类中。
     */
    @NotNull
    private final Logger logger;

    /**
     * loggerLevel: 日志级别。0->消息,1->警告,2->错误
     */
    @NotNull
    private final Integer loggerLevel;

    /**
     * 输出日志到日志文件中。
     */
    public void output() {
        switch (loggerLevel) {
            case 0:
                logger.info(content());
                break;
            case 1:
                logger.warn(content());
                break;
            default:
                logger.error(content());
        }
    }

    private String content() {
        return "\n" +
                "start ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" +
                "description ---> " + description + "\n" +
                "responseVO  ---> " + responseVO + "\n" +
                "throwable   ---> " + throwable + "\n" +
                "end   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}