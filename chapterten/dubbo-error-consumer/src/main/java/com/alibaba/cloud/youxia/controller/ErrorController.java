package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.DubboErrorService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class ErrorController {

    @DubboReference(version = "1.0.0",group = "dubbo-error-provider",timeout = 1000)
    private DubboErrorService dubboErrorService;

    @RequestMapping(value = "/error")
    public String simulationError(){
        return dubboErrorService.simulationError();
    }
}
