package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.DubboAlarmService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/alarm")
public class AlarmController {

    @DubboReference(version = "1.0.0",group = "dubbo-alarm-provider",timeout = 2000,retries = 3)
    private DubboAlarmService dubboAlarmService;

    @RequestMapping(value = "/query")
    public String alarm(){
        return dubboAlarmService.alarm();
    }
}
