package com.alibaba.cloud.youxia;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayAccessSentinelApplication {
    public static void main(String[] args) {
        InitExecutor.doInit();
        GatewayCallbackManager.setRequestOriginParser(s -> "123");
        SpringApplication.run(GatewayAccessSentinelApplication.class, args);

    }
}
