package com.alibaba.cloud.youxia;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelSpringCloudAlibabaApplication {
    public static void main(String[] args) {
        InitExecutor.doInit();
        SpringApplicationBuilder providerBuilder = new SpringApplicationBuilder();
        providerBuilder.web(WebApplicationType.NONE)
                .sources(SentinelSpringCloudAlibabaApplication.class).run(args);
    }
}
