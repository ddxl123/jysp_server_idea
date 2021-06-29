package com.example.demo.entityvo.responsevo;

import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * @author 10338
 */
@Getter
public class ResponseWithLog {

    @NotNull
    private final Logger logger;

    @NotNull
    private final boolean isErrorLog;

    @Nullable
    private final Throwable throwable;

    @NotNull
    private ResponseVO responseVO;

    /**
     * 设置 Logger，因为 Logger 没法自动注入到非配置类中。
     * 当该对象被创建时，就会输出 log。
     *
     * @param isErrorLog 如果为 true，则为 logger.error()，否则为 logger.info()。
     */
    public ResponseWithLog(Logger logger, boolean isErrorLog,@Nullable Throwable throwable) {
        this.logger = logger;
        this.isErrorLog = isErrorLog;
        this.throwable = throwable;
    }

    public void setResponseVO(ResponseVO responseVO) {
        this.responseVO = responseVO;
        output();
    }

    private void output() {
        if (isErrorLog) {
            logger.error("\nresponseVO ---> {}\nthrowable ---> ", responseVO, throwable);
        } else {
            logger.info("\nresponseVO ---> {}\nthrowable ---> ", responseVO, throwable);
        }

    }
}
