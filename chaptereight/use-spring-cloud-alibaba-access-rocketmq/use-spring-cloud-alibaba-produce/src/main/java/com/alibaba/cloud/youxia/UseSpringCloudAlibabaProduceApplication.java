package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.source.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({MySource.class})
public class UseSpringCloudAlibabaProduceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseSpringCloudAlibabaProduceApplication.class, args);
    }
}
