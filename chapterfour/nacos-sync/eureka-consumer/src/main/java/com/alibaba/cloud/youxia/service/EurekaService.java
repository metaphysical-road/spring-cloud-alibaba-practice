package com.alibaba.cloud.youxia.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class EurekaService {

    @Resource
    RestTemplate restTemplate;

    public String hiService() {
        return restTemplate.getForObject("http://EURKA-PROVIDER/data/getdata",String.class);
    }
}
