package com.alibaba.cloud.youxia;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelCoreAccessApplication {
    public static void main(String[] args) {
        InitExecutor.doInit();
        SpringApplication.run(SentinelCoreAccessApplication.class, args);
    }
}
