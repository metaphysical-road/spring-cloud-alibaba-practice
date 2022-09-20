package com.alibaba.cloud.youxia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    private AtomicInteger atomicInteger6756=new AtomicInteger();
    private AtomicInteger atomicInteger8030=new AtomicInteger();

    public void execute(){
        for(int i=0; i<10000;i++){
            String result = restTemplate.getForObject("http://127.0.0.1:28988/discovery-gray-route-trade-service-server/tradeManager/buygood",
                    String.class);
            System.out.println("结果为：" + result);
            if(result.contains("6756")) {
                atomicInteger6756.incrementAndGet();
            }else{
                atomicInteger8030.incrementAndGet();
            }
            Integer totalCount=atomicInteger6756.get()+atomicInteger8030.get();
            double ratio1=atomicInteger6756.get()*1.0d/totalCount;
            double ratio2=atomicInteger8030.get()*1.0d/totalCount;
            System.out.println("抽样调用"+i+"次");
            System.out.println("127.0.0.1:6756调用比例为："+ratio1);
            System.out.println("127.0.0.1:8030调用比例为："+ratio2);
        }
    }

    class AnalogThread implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    final Thread thread = Thread.currentThread();
                    if(countDownLatch.getCount()>0) {
                        String result = restTemplate.getForObject("http://127.0.0.1:28988/discovery-gray-route-trade-service-server/tradeManager/buygood",
                                String.class);
                        System.out.println(thread.getId() + ":结果为：" + result);
                        if(result.contains("6756")) {
                            atomicInteger6756.incrementAndGet();
                        }else{
                            atomicInteger8030.incrementAndGet();
                        }
                        countDownLatch.countDown();
                    }else {
                        Integer totalCount=atomicInteger6756.get()+atomicInteger8030.get();
                        double ratio1=atomicInteger6756.get()*1.0d/totalCount;
                        double ratio2=atomicInteger8030.get()*1.0d/totalCount;
                        System.out.println("127.0.0.1:6756调用比例为："+ratio1);
                        System.out.println("127.0.0.1:8030调用比例为："+ratio2);
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
