package com.alibaba.cloud.youxia.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

@Configuration
public class NacosConfig {

    @Bean
    public ConfigService configService() throws NacosException {
        final Properties properties = new Properties();
        //设置Nacos节点，对应的IP地址。
        properties.setProperty(PropertyKeyConst.SERVER_ADDR,"127.0.0.1:8848");
        //设置命名空间。
        properties.setProperty(PropertyKeyConst.NAMESPACE,"c7ba173f-29e5-4c58-ae78-b102be11c4f9");
        //如果开启Nacos权限校验，设置用户名。
        properties.setProperty(PropertyKeyConst.USERNAME,"nacos");
        //如果开启Nacos权限校验，设置用户密码。
        properties.setProperty(PropertyKeyConst.PASSWORD,"nacos");
        //设置获取配置信息的长轮训超时时间。
        properties.setProperty(PropertyKeyConst.CONFIG_LONG_POLL_TIMEOUT,"3000");
        //设置获取配置信息失败之后，重试的次数。
        properties.setProperty(PropertyKeyConst.CONFIG_RETRY_TIME,"5");
        //设置是否开启客户端主动拉取最新的配置信息。
        properties.setProperty(PropertyKeyConst.ENABLE_REMOTE_SYNC_CONFIG,"true");
        //设置最大重试次数。
        properties.setProperty(PropertyKeyConst.MAX_RETRY,"5");
        //构造一个ConfigService实例
        ConfigService configService = NacosFactory.createConfigService(properties);
        return configService;
    }
}
