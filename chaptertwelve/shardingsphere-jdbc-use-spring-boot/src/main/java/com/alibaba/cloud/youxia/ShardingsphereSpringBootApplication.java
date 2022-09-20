package com.alibaba.cloud.youxia;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@MapperScan(basePackages = "com.alibaba.cloud.youxia.mapper")
public class ShardingsphereSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereSpringBootApplication.class, args);
    }
}
