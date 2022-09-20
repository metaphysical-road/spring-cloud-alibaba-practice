package com.alibaba.cloud.youxia.service;

import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class LettuceService {
    @Autowired
    private StatefulRedisClusterConnection statefulRedisClusterConnection ;

    @PostConstruct
    public void initRedisResource(){
        RedisAdvancedClusterCommands<String,String> clusterCommands=statefulRedisClusterConnection.sync();
        clusterCommands.setex("rediscluster",100000,"true");
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(new ReadRedisCluster());
    }
    class ReadRedisCluster implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                RedisAdvancedClusterCommands<String,String> clusterCommands=statefulRedisClusterConnection.sync();
                String value=clusterCommands.get("rediscluster");
                System.out.println("Redis Cluster中的值为:"+value);
            }
        }
    }
}
