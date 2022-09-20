package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.service.PayService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "use-nacos-spring-boot",version = "1.0.0")
public class PayServiceImpl implements PayService {
    @Override
    public String pay() {
        return "payResult";
    }
}
