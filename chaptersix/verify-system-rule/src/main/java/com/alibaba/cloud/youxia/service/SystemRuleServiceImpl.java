package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.config.NacosConfig;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0",group = "verify-system-rule")
public class SystemRuleServiceImpl implements SystemRuleService{
    @Autowired
    private NacosConfig nacosConfig;

    @Override
    public String verifySystemRule() {
        if(nacosConfig.isEnablecpuandrt()){
            for(int i=0;i<900000000;i++){}
            for(int i=0;i<900000000;i++){}
            for(int i=0;i<900000000;i++){}
            for(int i=0;i<900000000;i++){}
            for(int i=0;i<900000000;i++){}
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }
        return "验证系统规则";
    }
}
