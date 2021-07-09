package com.example.demo.config;

import com.example.demo.interceptor.MainInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    final
    MainInterceptor mainInterceptor;

    public WebMvcConfig(MainInterceptor mainInterceptor) {
        this.mainInterceptor = mainInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mainInterceptor).addPathPatterns();
    }
}
