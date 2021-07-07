package com.example.demo.config.passwordconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {
    @Bean
    public Passworder passworder() {
        return new Passworder();
    }
}
