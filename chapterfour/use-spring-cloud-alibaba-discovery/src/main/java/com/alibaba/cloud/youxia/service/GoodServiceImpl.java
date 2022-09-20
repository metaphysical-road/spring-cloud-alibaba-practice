package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "example11",version = "1.0.0")
public class GoodServiceImpl implements GoodService{
    @Override
    public String getGoodName() {
        return "goodName";
    }
}
