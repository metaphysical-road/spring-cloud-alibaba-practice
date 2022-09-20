package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.atomic.AtomicLong;

@DubboService(version = "1.0.0",group = "dubbo-error-provider")
public class DubboErrorServiceImpl implements DubboErrorService{
    private AtomicLong count=new AtomicLong();

    @Override
    public String simulationError() {
        Long result=count.incrementAndGet();
        if(result%3==0){
            try {
                Thread.sleep(1000);
                for(int i=0;i<10000000;i++){
                }
            }catch (InterruptedException io){
                System.out.println(io.getMessage());
            }
            return "出现超时故障了！";
        }
        return "成功！";
    }
}
