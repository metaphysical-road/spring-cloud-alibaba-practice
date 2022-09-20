package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.config.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ MySink.class })
public class UseSpringCloudAlibabaConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseSpringCloudAlibabaConsumeApplication.class, args);
    }
}
