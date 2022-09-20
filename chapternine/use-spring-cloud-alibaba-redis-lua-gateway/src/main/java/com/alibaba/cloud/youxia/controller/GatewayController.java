package com.alibaba.cloud.youxia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/thread")
    public String executeThread(){
        ExecutorService executorService= Executors.newFixedThreadPool(20);
        executorService.execute(new GatewayThread());
        return "成功";
    }

    class GatewayThread implements Runnable{
        @Override
        public void run() {
            while (true){
                restTemplate.getForObject("http://127.0.0.1:28082/user/getUserInfo",String.class);
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
