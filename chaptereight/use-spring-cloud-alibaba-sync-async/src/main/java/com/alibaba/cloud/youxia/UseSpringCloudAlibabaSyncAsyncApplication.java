package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.rocketmq.SyncAsyncMessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ SyncAsyncMessageSource.class })
public class UseSpringCloudAlibabaSyncAsyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseSpringCloudAlibabaSyncAsyncApplication.class, args);
    }
}
