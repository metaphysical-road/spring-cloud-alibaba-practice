package com.alibaba.cloud.youxia.dao;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountinfoDAO {
    List<AccountinfoDO> getAllAccountinfo();
}
