package com.alibaba.cloud.youxia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alibaba.cloud.youxia.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("Spring Cloud Alibaba 微服务实战派")
                        .description("用户服务-幂等性验证")
                        .version("9.0")
                        .contact(new Contact("幂等性验证", "1234huxian@163.con", "1234huxian@163.com"))
                        .license("The Apache License")
                        .licenseUrl("1234huxian@163.com")
                        .build());
    }
}
