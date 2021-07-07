package com.example.demo.util.logger;

import com.example.demo.controller.responsevo.ResponseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author 10338
 */
@Getter
@AllArgsConstructor
public class Logout {

    /**
     * 对日志的描述。
     */
    @NonNull
    private final String description;

    /**
     * 响应给客户端的响应体。
     */
    @Nullable
    private final ResponseVO<?> responseVO;

    /**
     * 抛出的异常。
     */
    @Nullable
    private final Throwable throwable;

    /**
     * 设置 Logger，因为 Logger 没法自动注入到非配置类中。
     */
    @NonNull
    private final org.slf4j.Logger logger;

    /**
     * loggerLevel: 日志级别。0->消息,1->警告,2->错误
     */
    @NonNull
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
                "description ---> " + description + "\n" +
                "responseVO  ---> " + responseVO + "\n" +
                "throwable   ---> " + throwable + "\n" +
                "end   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}