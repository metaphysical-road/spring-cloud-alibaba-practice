package com.alibaba.cloud.youxia.manager;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProductInit {

    @Resource
    private ProductManager example2UserManager;

    @PostConstruct
    public void init(){
        ExecutorService executorService=Executors.newSingleThreadExecutor();
        executorService.execute(new OptLockThread(example2UserManager));
    }
}
