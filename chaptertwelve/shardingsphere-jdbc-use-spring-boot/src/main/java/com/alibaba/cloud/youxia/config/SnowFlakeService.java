package com.alibaba.cloud.youxia.config;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SnowFlakeService {

    Map<String, SnowFlake> snowFlakeHandlerMap = new ConcurrentHashMap<>();
    public long nextId() {
        long dataCenterId = 3L;
        long machineId = 7L;
        return nextId(dataCenterId, machineId);
    }

    public long nextId(final long datacenterId, final long machineId) {
        final long sdatacenterId = datacenterId;
        final long smachineId = machineId;
        final String handler = sdatacenterId + "_" + smachineId;
        SnowFlake snowFlake;
        if (snowFlakeHandlerMap.containsKey(handler)) {
            snowFlake = snowFlakeHandlerMap.get(handler);
            return snowFlake.nextId();
        } else {
            snowFlake = new SnowFlake(datacenterId, machineId);
            snowFlakeHandlerMap.putIfAbsent(handler, snowFlake);
            snowFlake = snowFlakeHandlerMap.get(handler);
            return snowFlake.nextId();
        }
    }
}
