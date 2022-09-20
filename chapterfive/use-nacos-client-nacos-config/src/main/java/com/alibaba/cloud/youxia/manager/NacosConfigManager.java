package com.alibaba.cloud.youxia.manager;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class NacosConfigManager {

    @Resource
    private ConfigService configService;

    @PostConstruct
    private void init() throws NacosException {
        String data=configService.getConfig("use-nacos-client-nacos-config","use-nacos-client-nacos-config",2000);
        System.out.println("通过Nacos Client 从Nacos配置中心获取配置信息为："+data);
    }

}
