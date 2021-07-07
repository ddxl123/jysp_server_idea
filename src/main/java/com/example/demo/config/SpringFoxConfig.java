package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 10338
 */
@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket createRestApi() {
        System.out.println("Api 接口文档可以访问网址: http://localhost:8080/swagger-ui/");
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // 只对以下起包作用
                .apis(RequestHandlerSelectors.basePackage(PackageNameConstant.CONTROLLER_PACKAGE))
                .build();
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs")
                .description("无描述")
                .contact(new Contact("联系邮箱: 1033839760@qq.com", "", "1033839760@qq.com"))
                .version("1.0")
                .build();

    }
}