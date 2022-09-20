package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.bo.Example5OrderBo;
import com.alibaba.cloud.youxia.mapper.Example5OrderMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class OrderQueryInit {

    @Resource
    private Example5OrderMapper example5OrderMapper;

    @PostConstruct
    public void init(){
        Example5OrderBo example5OrderBo=new Example5OrderBo();
        example5OrderMapper.selectOrder(example5OrderBo);
    }
}
