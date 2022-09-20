package com.alibaba.cloud.youxia;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "com.alibaba.cloud.youxia.mapper")
@EnableDiscoveryClient
public class ShardingsphereSpringCloudAlibabaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereSpringCloudAlibabaApplication.class, args);
    }
}
