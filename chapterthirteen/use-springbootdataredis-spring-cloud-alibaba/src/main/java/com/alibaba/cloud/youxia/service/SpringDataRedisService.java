package com.alibaba.cloud.youxia.service;
import com.alibaba.cloud.youxia.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SpringDataRedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void redisInit(){
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        User item=new User();
        item.setUserId(12345L);
        item.setUserName("测试");
        valueOperations.set("12345",item);
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(new RedisOperateThread());
    }

    class RedisOperateThread implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
                Object value=valueOperations.get("12345");
                ObjectMapper objectMapper = new ObjectMapper();
                //将map转化为json字符串
                String objStr="";
                try {
                    objStr = objectMapper.writeValueAsString(value);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
                User user = null;
                try {
                    //将json字符串转化为对象
                    user = objectMapper.readValue(objStr,User.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("通过线程获取，user:"+user.toString());
            }
        }
    }
}
