package com.alibaba.cloud.youxia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

@Configuration
@RefreshScope
public class ConfigReader {

    @Autowired
    private NacosConfig nacosConfig;

    @PostConstruct
    public void init() {
        Executors.newCachedThreadPool().execute(new ConfigThread());
    }

    class ConfigThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("使用Spring Cloud Alibaba接入Nacos配置中心，获取配置信息name为：" + nacosConfig.getName());
//                try {
//                    Thread.sleep(6000);
//                } catch (InterruptedException e) {
//                    System.out.println(e.getMessage());
//                }
            }
        }
    }
}
