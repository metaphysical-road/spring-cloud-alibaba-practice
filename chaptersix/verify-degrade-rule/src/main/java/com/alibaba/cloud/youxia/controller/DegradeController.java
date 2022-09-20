package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.DegradeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@RestController
public class DegradeController {

    @DubboReference(version = "1.0.0",group = "verify-degrade-rule")
    private DegradeService degradeService;

    @GetMapping(value = "/verify")
    @ResponseBody
    public String verify(){
        Executors.newFixedThreadPool(50).execute(new ExecutorThread());
        Executors.newFixedThreadPool(50).execute(new ExecutorThread());
        Executors.newFixedThreadPool(50).execute(new ExecutorThread());
        return "success";
    }
    class ExecutorThread implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    degradeService.verifyDegradeRule();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
