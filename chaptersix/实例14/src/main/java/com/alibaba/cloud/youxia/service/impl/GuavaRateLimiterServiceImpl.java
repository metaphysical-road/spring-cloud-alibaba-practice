package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.service.GuavaRateLimiterService;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class GuavaRateLimiterServiceImpl implements GuavaRateLimiterService {

    /**
     * 每秒钟只发出2个令牌，拿到令牌的请求才可以进入下一个业务
     */
    private RateLimiter seckillRateLimiter = RateLimiter.create(2);

    @Override
    public boolean tryAcquireSeckill() {
        return seckillRateLimiter.tryAcquire();
    }
}
