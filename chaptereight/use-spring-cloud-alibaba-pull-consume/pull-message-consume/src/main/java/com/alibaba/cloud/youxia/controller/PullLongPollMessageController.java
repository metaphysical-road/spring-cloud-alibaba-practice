package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.rocketmq.PullLongPollMessageReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/pullLongPollMessage")
public class PullLongPollMessageController {

    @Resource
    private PullLongPollMessageReceiveService pullLongPollMessageReceiveService;

    @GetMapping(value = "/poll")
    public String pollMessage(){
        pullLongPollMessageReceiveService.pollMessage();
        return "true";
    }
}
