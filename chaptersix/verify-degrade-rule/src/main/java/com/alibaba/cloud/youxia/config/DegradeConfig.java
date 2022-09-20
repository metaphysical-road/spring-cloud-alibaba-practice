package com.alibaba.cloud.youxia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executors;

@Configuration
public class DegradeConfig {

//    @Bean
//    public RestTemplate newRestTemplate(){
//        RestTemplate restTemplate=new RestTemplate();
//        return restTemplate;
//    }
//
//    @Resource
//    private RestTemplate restTemplate;
//
//    @PostConstruct
//    public void init(){
//        try {
//            Thread.sleep(10000);
//        }catch (InterruptedException e){
//        }
//        Executors.newFixedThreadPool(50).execute(new ExecutorThread());
//    }
//
//    class ExecutorThread implements Runnable{
//
//        @Override
//        public void run() {
//            while (true) {
//                restTemplate.getForObject("http:127.0.0.1:7867/verify", String.class);
//                try{
//                    Thread.sleep(500);
//                }catch (InterruptedException e){
//
//                }
//            }
//        }
//    }
}
