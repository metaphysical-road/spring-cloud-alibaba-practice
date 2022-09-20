package com.alibaba.cloud.youxia.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author clay
 * @version 1.0
 */
@Configuration
@MapperScan("com.alibaba.cloud.youxia.mapper")
public class MyBatisConfig {
}
