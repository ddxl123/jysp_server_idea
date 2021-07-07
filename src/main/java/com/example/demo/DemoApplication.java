package com.example.demo;

import com.example.demo.config.PackageNameConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * {@link EnableOpenApi} 启用 springfox
 * <p>
 * {@link MapperScan} mybatis-plus 需要
 * <p>
 * {@link EnableWebSecurity} 只用上了密码加密器
 *
 * @author 10338
 */
@SpringBootApplication
@EnableOpenApi
@EnableWebSecurity
@MapperScan(basePackages = PackageNameConstant.MAPPER_PACKAGE)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
