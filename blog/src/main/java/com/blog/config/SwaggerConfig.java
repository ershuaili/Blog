package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 委稼祥
 * @date : 2022/5/15 19:37
 * @description : swagger配置类
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @SuppressWarnings("all")
    public ApiInfo apiInfo() {
        Contact contact = new Contact("blog", "", "");
        return new ApiInfoBuilder()
                .title("Apidoc")
                .description("API接口测试")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}
