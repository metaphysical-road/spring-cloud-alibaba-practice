package com.alibaba.cloud.youxia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class StarterConfig {

    @Resource
    private GrayConfig grayConfig;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private AtomicInteger atomicIntegerGray=new AtomicInteger();
    private AtomicInteger atomicIntegerStable=new AtomicInteger();
    public String execute(String url) {
        HttpHeaders headers = new HttpHeaders();
        if (grayConfig.getLabel().equals("open")) {
            if (grayConfig.getGrayType().equals("gray-condition-1")) {
                headers.add("a", "1");
            } else if (grayConfig.getGrayType().equals("gray-condition-2")) {
                headers.add("a", "1");
                headers.add("b", "2");
            } else if (grayConfig.getGrayType().equals("gray-condition-3")) {
                headers.add("a", "1");
                headers.add("b", "3");
            } else if (grayConfig.getGrayType().equals("gray-condition-4")) {
                headers.add("a", "1");
                headers.add("b", "4");
            } else if (grayConfig.getGrayType().equals("gray-condition-5")) {
                headers.add("a", "1");
                headers.add("b", "5");
            } else if (grayConfig.getGrayType().equals("gray-condition-6")) {
                headers.add("a", "1");
                headers.add("b", "6");
            } else if (grayConfig.getGrayType().equals("basic-condition")) {
                //do nothing
            }
        }
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<String>(null, headers), String.class);
        System.out.println("结果为：" + result.getBody());
        String applicationName="gray-publish-order-server";
        if (null != result.getBody() && !"".equals(result.getBody())) {
            if (result.getBody().contains(applicationName+","+"GrayRoute")) {
                atomicIntegerGray.incrementAndGet();
            } else if (result.getBody().contains(applicationName+","+"StableRoute")) {
                atomicIntegerStable.incrementAndGet();
            }
        }
        Integer totalCount = atomicIntegerGray.get() + atomicIntegerStable.get();
        double ratio1 = atomicIntegerGray.get() * 1.0d / totalCount;
        double ratio2 = atomicIntegerStable.get() * 1.0d / totalCount;
        System.out.println("抽样调用" + totalCount + "次");
        System.out.println("灰度路由调用比例为：" + ratio1);
        System.out.println("稳定路由调用比例为：" + ratio2);
        return result.getBody();
    }
}
