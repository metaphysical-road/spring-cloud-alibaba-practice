package com.alibaba.cloud.youxia.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/webMvcController")
@SentinelResource("webMvcController")
public class WebMvcController {
    @GetMapping("/hello")
    @ResponseBody
    public String apiHello() {
        doBusiness();
        return "Hello!";
    }
    private void doBusiness() {
        Random random = new Random(1);
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
