package com.example.demo;

import com.example.demo.config.GlobalConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * {@link EnableOpenApi} 启用 springfox
 * {@link MapperScan} mybatis-plus 需要
 * @author 10338
 */
@SpringBootApplication
@EnableOpenApi
@MapperScan(GlobalConstant.MAPPER_PACKAGE)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}