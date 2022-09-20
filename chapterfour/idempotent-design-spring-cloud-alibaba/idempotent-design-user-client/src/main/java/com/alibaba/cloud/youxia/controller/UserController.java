package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.config.NacosConfig;
import com.alibaba.cloud.youxia.dto.UserDTO;
import com.alibaba.cloud.youxia.request.UserServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;
import com.alibaba.cloud.youxia.response.Result;
import com.alibaba.cloud.youxia.service.DistributedService;
import com.alibaba.cloud.youxia.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "/user")
public class UserController {

    @DubboReference(version = "1.0.0",group ="idempotent-design-user-server",retries = 0)
    private UserService userService;

    @DubboReference(version = "1.0.0",group = "distributed-uuid-server")
    private DistributedService distributedService;

    @Autowired
    private NacosConfig nacosConfig;

    @GetMapping(value = "/getUserInfo")
    @ResponseBody
    public Result<List<UserDTO>> getUserInfo(@RequestParam String userName){
        long uuid=distributedService.nextId(7,8);
        UserServiceRequest<String> request=new UserServiceRequest();
        request.setRequestData("调用用户服务");
        request.setUserName(userName);
        request.setUuid(uuid+"");
        //return new ArrayList<UserDTO>();
        DefaultResult<List<UserDTO>> result=new DefaultResult<>();
        if(nacosConfig.isMideng()){
         //模拟幂等性场景-超时重试 100次
            for(int i=0;i<100;i++){
                result=userService.getUserInfo(request);
            }
        }else{
            result=userService.getUserInfo(request);
        }
        return result;
    }
}
