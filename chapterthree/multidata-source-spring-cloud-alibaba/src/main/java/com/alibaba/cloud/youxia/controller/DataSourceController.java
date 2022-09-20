package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.bo.Example2ProductBo;
import com.alibaba.cloud.youxia.entity.Example2ProductEntity;
import com.alibaba.cloud.youxia.service.Example4UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController(value = "/datasource")
public class DataSourceController {

    @Resource
    private Example4UserService example4UserService;

    @GetMapping(value = "/getOrder")
    @ResponseBody
    public String toGet(@RequestParam Long goodId){
        //5678L
        Example2ProductBo example2ProductBo=new Example2ProductBo();
        example2ProductBo.setGoodId(goodId);
        List<Example2ProductEntity> result1= example4UserService.selectFromMaster(example2ProductBo);
        List<Example2ProductEntity> result2= example4UserService.selectFromSlave1(example2ProductBo);
        List<Example2ProductEntity> result3= example4UserService.selectFromSlave2(example2ProductBo);
        return "success";
    }
}
