package com.alibaba.cloud.youxia.util;

import com.alibaba.cloud.youxia.config.AlarmConfigService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class AlarmRedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private AlarmConfigService alarmConfigService;

    private static final String ALARM_ID_CACHE = "alarm_api:alarm_id_cache_%s";

    public boolean isExistAlarmId(String id0) {
        return null!=(stringRedisTemplate.opsForValue().get(String.format(ALARM_ID_CACHE, id0)));
    }
    public void updateAlarmId(String id0) {
        stringRedisTemplate.opsForValue().set(String.format(ALARM_ID_CACHE, id0),"1",2L,
                TimeUnit.SECONDS);
    }
}
