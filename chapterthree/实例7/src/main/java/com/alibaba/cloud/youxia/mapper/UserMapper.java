package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 自定义SQL：默认也会增加多租户条件
     * 参考打印的SQL
     * @return
     */
    public Integer myCount();

    public List<UserEntity> getUserAndAddr(@Param("username") String username);

    public List<UserEntity> getAddrAndUser(@Param("name") String name);
}
