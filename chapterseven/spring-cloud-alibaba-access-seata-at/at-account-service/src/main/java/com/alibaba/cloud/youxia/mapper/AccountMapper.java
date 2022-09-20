package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.bo.AccountBo;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AccountMapper{
    Integer deductAccountBalance(AccountBo accountBo);
}
