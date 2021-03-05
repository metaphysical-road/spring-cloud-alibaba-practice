package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/order")
public class OrderController {

    @DubboReference(version = "1.0.0", group = "example4-springcloud-user-server")
    private OrderService orderService;

    @GetMapping(value = "/getOrderInfo")
    public String getOrderInfo() {
        return orderService.getOrderInfo();
    }
}
