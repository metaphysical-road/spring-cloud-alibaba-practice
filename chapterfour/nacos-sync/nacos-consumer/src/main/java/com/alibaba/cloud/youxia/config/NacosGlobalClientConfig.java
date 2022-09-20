package com.alibaba.cloud.youxia.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name ="EURKA-PROVIDER",configuration =NacosRibbonRuleConfig.class)
public class NacosGlobalClientConfig {
}
