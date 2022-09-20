package com.alibaba.cloud.youxia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alibaba.cloud.youxia.mapper")
public class Example7Application {
    public static void main(String[] args) {
        SpringApplication.run(Example7Application.class, args);
    }
}
