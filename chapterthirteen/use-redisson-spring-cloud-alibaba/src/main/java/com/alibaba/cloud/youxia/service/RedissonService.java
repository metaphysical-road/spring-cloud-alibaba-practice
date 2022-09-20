package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.pojo.Good;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomUtils;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RedissonService {

    private String cacheMapName;
    private String cacheListName;
    private String cacheSetName;
    private RMap<String,String> rMap;
    private RSet<Good> rSet;
    private RList<Good> rList;

    @Autowired
    private RedissonClient redissonClient;

    @PostConstruct
    public void initRedis(){
        cacheMapName="test1234";
        cacheListName="testlist1234";
        cacheSetName="testSet1234";
        this.rList=redissonClient.getList(cacheListName);
        this.rMap = redissonClient.getMap(this.cacheMapName);
        this.rSet = redissonClient.getSet(this.cacheSetName);
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(new ReadThread());
    }

    public void put(String key,String value,Good item){
        rMap.put(key,value);
        rList.add(item);
        rSet.add(item);
    }

    class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                Good item = new Good();
                String key = RandomUtils.nextLong() + "";
                item.setGoodId(Long.valueOf(key));
                item.setGoodName("商品名称");
                String objectJson = JSON.toJSONString(item);
                put(key, objectJson, item);
                String value = rMap.get(key);
                System.out.println("缓存Map中的值为：" + value);
                Iterator<Good> iteratorList = rList.iterator();
                while (iteratorList.hasNext()) {
                    Good item1 = (Good) iteratorList.next();
                    System.out.println("缓存List中的值为：" + item1.toString());
                }
                Iterator<Good> iteratorSet = rSet.iterator();
                while (iteratorSet.hasNext()) {
                    Good item2 = (Good) iteratorSet.next();
                    System.out.println("缓存Set中的值为：" + item2.toString());
                }
            }
        }
    }
}
