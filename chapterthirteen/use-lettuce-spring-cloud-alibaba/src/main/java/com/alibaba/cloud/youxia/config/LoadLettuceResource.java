package com.alibaba.cloud.youxia.config;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadLettuceResource {
    @Autowired
    private LettuceConfig lettuceConfig;
    //配置集群选项,自动重连,最多重定型1次
    @Bean
    ClusterClientOptions clusterClientOptions(){
        return ClusterClientOptions.builder().autoReconnect(true).maxRedirects(1).build();
    }
    //建立集群客户端
    @Bean
    RedisClusterClient redisClusterClient(ClusterClientOptions options){
        List<RedisURI> arrayList=new ArrayList<>();
        List<String> hostUrlList=lettuceConfig.getHostUrlList();
        for(String s:hostUrlList){
            RedisURI redisURI=RedisURI.create(s);
            redisURI.setPassword(lettuceConfig.getPassword());
            arrayList.add(redisURI);
        }
        RedisClusterClient redisClusterClient = RedisClusterClient.create(arrayList);
        redisClusterClient.setOptions(options);
        return redisClusterClient;
    }
    //集群连接
    @Bean(destroyMethod = "close")
    StatefulRedisClusterConnection<String,String> statefulRedisClusterConnection(RedisClusterClient redisClusterClient){
        return redisClusterClient.connect();
    }
}
