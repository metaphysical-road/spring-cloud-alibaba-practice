package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.bo.Example2ProductBo;
import com.alibaba.cloud.youxia.config.CallConfig;
import com.alibaba.cloud.youxia.request.GoodServiceRequest;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Service
public class CallService {
    @Resource
    private CallConfig callConfig;

    @DubboReference(version = "1.0.0",group = "use-redisson-distribute-lock-spring-cloud-alibaba",timeout = 3000,
            actives = 20,connections = 20)
    private GoodService goodService;
    @PostConstruct
    public void initCall(){
        ExecutorService executorService=Executors.newFixedThreadPool(50);
        executorService.execute(new CallThread());
    }
    class CallThread implements Runnable{
        @Override
        public void run() {
            while (true){
                if(callConfig.isRemote()){
                    final Thread thread=Thread.currentThread();
                    GoodServiceRequest goodServiceRequest = new GoodServiceRequest();
                    goodServiceRequest.setUuid(RandomUtils.nextLong() +"");
                    goodServiceRequest.setThreadId(thread.getId()+"");
                    Example2ProductBo example2ProductBo = new Example2ProductBo();
                    //扣减同一个商品的库存
                    example2ProductBo.setGoodId(7878L);
                    example2ProductBo.setId(3467374334L);
                    goodServiceRequest.setRequestData(example2ProductBo);
                    System.out.println("开始扣减库存：" + example2ProductBo.toString()+" 扣减库存的源线程ID为："+goodServiceRequest.getThreadId()
                    +" 扣减库存的分布式发号器ID为："+goodServiceRequest.getUuid());
                    goodService.updateGoodNum(goodServiceRequest);
                }
            }
        }
    }
}
