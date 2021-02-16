package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.dao.AccountinfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountinfoMapper {
    @Select("select * from account_info where id>0")
    List<AccountinfoDO> getAllAccountinfo();
}
