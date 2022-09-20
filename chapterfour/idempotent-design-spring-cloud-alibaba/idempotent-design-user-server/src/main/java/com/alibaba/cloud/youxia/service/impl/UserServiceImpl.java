package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.dto.UserDTO;
import com.alibaba.cloud.youxia.entity.UserEntity;
import com.alibaba.cloud.youxia.mapper.UserMapper;
import com.alibaba.cloud.youxia.request.UserServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;
import com.alibaba.cloud.youxia.service.DistributedService;
import com.alibaba.cloud.youxia.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@DubboService(version = "1.0.0",group = "idempotent-design-user-server")
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @DubboReference(version = "1.0.0",group = "distributed-uuid-server")
    private DistributedService distributedService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public DefaultResult<List<UserDTO>> getUserInfo(UserServiceRequest userServiceRequest) {
        DefaultResult<List<UserDTO>> result=new DefaultResult<>();
        long uuid=Long.valueOf(userServiceRequest.getUuid());
        if(null!=redisTemplate.opsForValue().get(uuid)){
            result.setData(new ArrayList<>());
            if(null!=redisTemplate.opsForValue().get(uuid)){
                result.setData(new ArrayList<>());
                result.setMessage("uuid:"+uuid+" 已经连续访问多次！");
                return result;
            }else{
                redisTemplate.opsForValue().set(uuid,true,2, TimeUnit.SECONDS);
            }
            result.setMessage("uuid:"+uuid+" 已经连续访问多次！");
            return result;
        }else{
            redisTemplate.opsForValue().set(uuid,true,2, TimeUnit.SECONDS);
        }
        String userName=userServiceRequest.getUserName();
        List<UserEntity> queryResult=userMapper.getUserAndAddr(userName);
        List<UserDTO> dataResult=new ArrayList<>();
        if(!CollectionUtils.isEmpty(queryResult)){
            for (UserEntity userEntity:queryResult) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(userEntity.getId());
                userDTO.setAddrName(userEntity.getAddrName());
                userDTO.setTenantId(userEntity.getTenantId());
                userDTO.setName(userEntity.getName());
                dataResult.add(userDTO);
            }
        }
        result.setData(dataResult);
        return result;
    }
}
