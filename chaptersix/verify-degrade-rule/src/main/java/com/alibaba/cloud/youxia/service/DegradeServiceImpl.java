package com.alibaba.cloud.youxia.service;

import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group = "verify-degrade-rule")
public class DegradeServiceImpl implements DegradeService{
    //如果降级生效，不会调用方法verifyDegradeRule()
    @Override
    public String verifyDegradeRule() {
        int r= RandomUtils.nextInt(1,3);
        //设置错误比率为90%
        if(!("1".equals(r+""))) {
            //随机抛出异常，让Sentinel统计错误的请求数。
            throw new RuntimeException("错误");
        }
        for(int i=0;i<1000000000;i++){

        }
        return "verify degrade rule";
    }
}
