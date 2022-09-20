package com.alibaba.cloud.youxia.config;

import com.alibaba.cloud.youxia.gateway.GatewayDiscoveryEnabledStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public GatewayDiscoveryEnabledStrategy gatewayDiscoveryEnabledStrategy() {
        return new GatewayDiscoveryEnabledStrategy();
    }

}
