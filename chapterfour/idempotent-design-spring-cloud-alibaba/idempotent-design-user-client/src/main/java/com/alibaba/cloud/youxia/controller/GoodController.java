package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.bo.Example2ProductBo;
import com.alibaba.cloud.youxia.config.NacosConfig;
import com.alibaba.cloud.youxia.dto.GoodDTO;
import com.alibaba.cloud.youxia.request.GoodServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;
import com.alibaba.cloud.youxia.service.DistributedService;
import com.alibaba.cloud.youxia.service.GoodService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/good")
public class GoodController {
    @DubboReference(version = "1.0.0", group = "idempotent-design-user-server", retries = 4,timeout = 3000)
    private GoodService goodService;

    @DubboReference(version = "1.0.0", group = "distributed-uuid-server")
    private DistributedService distributedService;

    @Autowired
    private NacosConfig nacosConfig;

    @PostMapping(value = "/updataGoodNum")
    public DefaultResult<GoodDTO> updateGoodNum(@RequestParam("goodId") String goodId) {
        long uuid = distributedService.nextId(7, 8);
        GoodServiceRequest<Example2ProductBo> request = new GoodServiceRequest<Example2ProductBo>();
        Example2ProductBo example2ProductBo = new Example2ProductBo();
        example2ProductBo.setGoodId(Long.valueOf(goodId));
        request.setRequestData(example2ProductBo);
        if (nacosConfig.isMideng()) {
            request.setUuid(uuid + "");
        }
        return goodService.updateGoodNum(request);
    }
}
