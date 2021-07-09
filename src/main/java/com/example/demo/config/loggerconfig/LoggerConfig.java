package com.example.demo.config.loggerconfig;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 10338
 */
@Configuration
public class LoggerConfig {

    @Bean
    public org.slf4j.Logger getLogger() {
        return LoggerFactory.getLogger(org.slf4j.Logger.class);
    }
}
