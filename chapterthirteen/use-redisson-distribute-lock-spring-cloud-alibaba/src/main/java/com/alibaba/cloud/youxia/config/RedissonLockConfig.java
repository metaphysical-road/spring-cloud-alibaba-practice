package com.alibaba.cloud.youxia.config;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonLockConfig {

    @Autowired
    private RedissonClient redissonClient;

    @Bean(name ="goodRLock")
    public RLock initGoodRLock(){
        RLock rLock=redissonClient.getLock("goodRLock");
        return rLock;
    }
}
