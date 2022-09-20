package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.dto.UserDTO;
import com.alibaba.cloud.youxia.request.UserServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;

import java.util.List;

public interface UserService {
    DefaultResult<List<UserDTO>> getUserInfo(UserServiceRequest userServiceRequest);
}
