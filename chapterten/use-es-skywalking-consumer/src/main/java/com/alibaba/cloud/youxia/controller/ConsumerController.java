package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.bo.AnchorBo;
import com.alibaba.cloud.youxia.service.AnchorService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    @DubboReference(version = "1.0.0",group = "use-es-skywalking-provider")
    private AnchorService anchorService;

    @GetMapping(value = "/todo")
    public String consumer(){
        AnchorBo anchorBo=new AnchorBo();
        anchorBo.setAnchorLevel(RandomUtils.nextInt()+"");
        anchorBo.setAnchorName("test"+RandomUtils.nextLong());
        anchorService.insertAnchor(anchorBo);
        return "success";
    }
}
