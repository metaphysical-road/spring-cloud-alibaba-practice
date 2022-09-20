package com.alibaba.cloud.youxia.config;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class StarterConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @PostConstruct
    public void init() {
        execute();
    }
    @Resource
    private RestTemplate restTemplate;

    private CountDownLatch countDownLatch=new CountDownLatch(100);
    private AtomicInteger atomicIntegerBlue=new AtomicInteger();
    private AtomicInteger atomicIntegerGreen=new AtomicInteger();
    private AtomicInteger atomicIntegerDefault=new AtomicInteger();

    public void execute() {
        for (int i = 0; i < 10000; i++) {
            try {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e1){
                    System.out.println(e1.getMessage());
                }
                HttpHeaders headers = new HttpHeaders();
                headers.add("a","1");
                headers.add("b","3");
                ResponseEntity<String> result = restTemplate.exchange("http://127.0.0.1:28920/blue-green-trade-server/trade/buyGood", HttpMethod.GET,
                        new HttpEntity<String>(null,headers),String.class);
                System.out.println("结果为：" + result.getBody());
                if(null!=result.getBody()&&!"".equals(result.getBody())){
                    if (result.getBody().contains("blueRoute")) {
                        atomicIntegerBlue.incrementAndGet();
                    } else if (result.getBody().contains("greenRoute")){
                        atomicIntegerGreen.incrementAndGet();
                    } else if (result.getBody().contains("defaultRoute")){
                        atomicIntegerDefault.incrementAndGet();
                    }
                }
                Integer totalCount = atomicIntegerBlue.get() + atomicIntegerGreen.get() + atomicIntegerDefault.get();
                double ratio1 = atomicIntegerBlue.get() * 1.0d / totalCount;
                double ratio2 = atomicIntegerGreen.get() * 1.0d / totalCount;
                double ratio3 = atomicIntegerDefault.get() * 1.0d / totalCount;
                System.out.println("抽样调用" + i + "次");
                System.out.println("蓝路由调用比例为：" + ratio1);
                System.out.println("绿路由调用比例为：" + ratio2);
                System.out.println("兜底路由调用比例为：" + ratio3);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
