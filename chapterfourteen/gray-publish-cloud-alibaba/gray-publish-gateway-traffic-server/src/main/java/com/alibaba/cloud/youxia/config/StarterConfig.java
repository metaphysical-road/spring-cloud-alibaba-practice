package com.alibaba.cloud.youxia.config;

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

    @Resource
    private GrayConfig grayConfig;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @PostConstruct
    public void init() {
//        execute();
    }
    @Resource
    private RestTemplate restTemplate;

    private CountDownLatch countDownLatch=new CountDownLatch(100);
    private AtomicInteger atomicIntegerGray=new AtomicInteger();
    private AtomicInteger atomicIntegerStable=new AtomicInteger();
    public void execute() {
        for (int i = 0; i < 10000; i++) {
            try {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e1){
                    System.out.println(e1.getMessage());
                }
                HttpHeaders headers = new HttpHeaders();
                if (grayConfig.getLabel().equals("open")) {
                    if(grayConfig.getGrayType().equals("gray-condition-1")){
                        headers.add("a","1");
                    }else if(grayConfig.getGrayType().equals("gray-condition-2")){
                        headers.add("a","1");
                        headers.add("b","2");
                    }else if(grayConfig.getGrayType().equals("gray-condition-3")){
                        headers.add("a","1");
                        headers.add("b","3");
                    }else if(grayConfig.getGrayType().equals("gray-condition-4")){
                        headers.add("a","1");
                        headers.add("b","4");
                    }else if(grayConfig.getGrayType().equals("gray-condition-5")){
                        headers.add("a","1");
                        headers.add("b","5");
                    }else if(grayConfig.getGrayType().equals("gray-condition-6")){
                        headers.add("a","1");
                        headers.add("b","6");
                    }else if(grayConfig.getGrayType().equals("basic-condition")){
                        //do nothing
                    }
                }
                ResponseEntity<String> result = restTemplate.exchange("http://127.0.0.1:28920/blue-green-trade-server/trade/buyGood", HttpMethod.GET,
                        new HttpEntity<String>(null,headers),String.class);
                System.out.println("结果为：" + result.getBody());
                if(null!=result.getBody()&&!"".equals(result.getBody())){
                    if (result.getBody().contains("blueRoute")) {
                        atomicIntegerGray.incrementAndGet();
                    } else if (result.getBody().contains("greenRoute")){
                        atomicIntegerStable.incrementAndGet();
                    }
                }
                Integer totalCount = atomicIntegerGray.get() + atomicIntegerStable.get();
                double ratio1 = atomicIntegerGray.get() * 1.0d / totalCount;
                double ratio2 = atomicIntegerStable.get() * 1.0d / totalCount;
                System.out.println("抽样调用" + i + "次");
                System.out.println("灰度路由调用比例为：" + ratio1);
                System.out.println("稳定路由调用比例为：" + ratio2);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
