package com.alibaba.cloud.youxia.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.alibaba.cloud.youxia.mapper")
public class MybatisPlusOptLockerConfig {
    /**
     * 1. 注入一个Mybatis Plus的拦截器MybatisPlusInterceptor，并绑定一个子拦截器OptimisticLockerInnerInterceptor
     * 2. OptimisticLockerInnerInterceptor是用来实现乐观锁的核心类
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //1.新建一个拦截器对象MybatisPlusInterceptor
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //2.添加子拦截器OptimisticLockerInnerInterceptor
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //3.返回给Spring Framework的IOC容器
        return interceptor;
    }
}
