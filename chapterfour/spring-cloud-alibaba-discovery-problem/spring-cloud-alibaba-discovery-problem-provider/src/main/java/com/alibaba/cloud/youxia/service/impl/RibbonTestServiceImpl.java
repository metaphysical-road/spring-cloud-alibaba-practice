package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.service.RibbonTestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class RibbonTestServiceImpl implements RibbonTestService {
    @Override
    public String test() {
        return "test";
    }
}
