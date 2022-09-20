package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.pojo.Order;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class JedisService {

    @Autowired
    private JedisPool jedisPool;

    @PostConstruct
    public void initJedis(){
        Jedis source=jedisPool.getResource();
        Order item=new Order();
        item.setOrderAmount(new BigDecimal(100));
        item.setOrderId(10000L);
        item.setOrderName("测试订单");
        String objectJson = JSON.toJSONString(item);
        source.set("10000",objectJson);
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(new ReadValueThread());
    }

    class ReadValueThread implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                Jedis source=jedisPool.getResource();
                String value=source.get("10000");
                System.out.println("用Jedis客户端从Codis服务端获取值为："+value);
            }
        }
    }
}
