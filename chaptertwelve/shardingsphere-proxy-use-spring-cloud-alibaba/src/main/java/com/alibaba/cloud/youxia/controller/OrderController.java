package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.entity.Order;
import com.alibaba.cloud.youxia.manager.OrderManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Resource
    private OrderManager orderManager;
    @GetMapping(value = "/insert")
    @ResponseBody
    public String insertOrder(){
        Integer result=orderManager.insertOrderInfo();
        if(result>0){
            return "success";
        }else{
            return "fail";
        }
    }
    @GetMapping(value = "/select")
    @ResponseBody
    public String queryOrder(){
        List<Order> items= orderManager.selectOrderInfo();
        return "success";
    }
}
