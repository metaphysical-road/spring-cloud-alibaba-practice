package com.alibaba.cloud.youxia.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

@Component
public class TenantThreadExecutor {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init(){
        Executors.newSingleThreadExecutor().execute(new ExecuteThread(userService));
    }
}
