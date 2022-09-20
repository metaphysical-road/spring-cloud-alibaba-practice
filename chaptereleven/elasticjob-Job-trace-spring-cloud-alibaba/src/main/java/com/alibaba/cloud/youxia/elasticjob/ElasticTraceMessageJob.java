package com.alibaba.cloud.youxia.elasticjob;

import com.alibaba.cloud.youxia.config.ElasticJobTraceConfig;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ElasticTraceMessageJob implements SimpleJob {
    @Resource
    private ElasticJobTraceConfig elasticJobTraceConfig;
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("执行Job时的系统时间为：" + new Date().toString());
        if (elasticJobTraceConfig.getIsException().equals("true")) {
            throw new RuntimeException("执行Job故障！");
        } else {
            if (elasticJobTraceConfig.getOnSleep().equals("true")) {
                try {
                    Thread.sleep(Long.valueOf(elasticJobTraceConfig.getSleepTime()));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("发送定时消息！");
        }
    }
}
