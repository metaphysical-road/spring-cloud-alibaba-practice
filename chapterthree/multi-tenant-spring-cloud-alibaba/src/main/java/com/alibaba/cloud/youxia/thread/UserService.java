package com.alibaba.cloud.youxia.thread;

import com.alibaba.cloud.youxia.entity.UserEntity;
import com.alibaba.cloud.youxia.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserEntity>  getUserAndAddr(String userName){
        return userMapper.getUserAndAddr(userName);
    }
}
