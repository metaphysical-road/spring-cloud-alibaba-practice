package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.manager.AlarmManager;
import com.alibaba.cloud.youxia.service.DubboAlarmService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0",group = "dubbo-alarm-provider")
public class DubboAlarmServiceImpl implements DubboAlarmService {
    @Autowired
    private AlarmManager alarmManager;
    @Override
    public String alarm() {
        return alarmManager.alarm();
    }
}
