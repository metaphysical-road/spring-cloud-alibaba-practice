package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.dto.UserDTO;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    public UserDTO getLoginUser(){
        List<UserDTO> userCache=new ArrayList<>();
        UserDTO userDTO1=new UserDTO();
        userDTO1.setTenantId(6767L);
        userDTO1.setName("租户A");
        userCache.add(userDTO1);

        UserDTO userDTO2=new UserDTO();
        userDTO2.setTenantId(7878L);
        userDTO2.setName("租户B");
        userCache.add(userDTO2);

        UserDTO userDTO3=new UserDTO();
        userDTO2.setTenantId(8989L);
        userDTO2.setName("租户C");
        userCache.add(userDTO3);
        int index= RandomUtils.nextInt(0,2);
        return userCache.get(index);
    }
}
