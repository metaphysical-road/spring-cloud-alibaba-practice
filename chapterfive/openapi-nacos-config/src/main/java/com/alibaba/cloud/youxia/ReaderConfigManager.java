package com.alibaba.cloud.youxia;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReaderConfigManager {

    @Resource
    private RestTemplate restTemplate;

    //使用RestTemplate访问Nacos Config的Open API
    @PostConstruct
    public void readConfig(){
        String url="http://127.0.0.1:8848/nacos/v1/cs/configs?dataId={dataId}&group={group}&tenant={tenant}";
        Map<String,String> params=new HashMap<String,String>(16);
        params.put("dataId", "openapi-nacos-config");
        params.put("group", "openapi-nacos-config");
        params.put("tenant","c7ba173f-29e5-4c58-ae78-b102be11c4f9");
        //入参
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class,params);
        String result=responseEntity.getBody();
        System.out.println("通过Open API 从Nacos配置中心获取配置信息为："+result);
    }
}
