package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.bo.Example2ProductBo;
import com.alibaba.cloud.youxia.config.CallConfig;
import com.alibaba.cloud.youxia.request.GoodServiceRequest;
import com.alibaba.cloud.youxia.service.GoodService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/call")
public class CallController {
    @Resource
    private CallConfig callConfig;

    @DubboReference(version = "1.0.0",group = "use-redisson-distribute-lock-spring-cloud-alibaba")
    private GoodService goodService;

    @GetMapping(value = "/callServer")
    public String callDistributeServer() {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.execute(new CallThread());
//        GoodServiceRequest goodServiceRequest = new GoodServiceRequest();
//        Example2ProductBo example2ProductBo = new Example2ProductBo();
//        example2ProductBo.setGoodId(7878L);
//        example2ProductBo.setId(3467374334L);
//        goodServiceRequest.setRequestData(example2ProductBo);
//        System.out.println("开始扣减库存：" + example2ProductBo.toString());
//        goodService.updateGoodNum(goodServiceRequest);
        return "success";
    }

    class CallThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (true) {
                if (callConfig.isRemote()) {
                    GoodServiceRequest goodServiceRequest = new GoodServiceRequest();
                    Example2ProductBo example2ProductBo = new Example2ProductBo();
                    example2ProductBo.setGoodId(7878L);
                    example2ProductBo.setId(3467374334L);
                    goodServiceRequest.setRequestData(example2ProductBo);
                    System.out.println("开始扣减库存：" + example2ProductBo.toString());
                    goodService.updateGoodNum(goodServiceRequest);
                }
            }
        }
    }
}
