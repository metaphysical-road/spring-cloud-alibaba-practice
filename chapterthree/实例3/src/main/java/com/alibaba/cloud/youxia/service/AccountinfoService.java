package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.dao.AccountinfoDO;
import com.alibaba.cloud.youxia.mapper.AccountinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountinfoService {

    @Autowired
    private AccountinfoMapper accountinfoMapper;

    @Transactional
    public List<AccountinfoDO> getAccountinfo(){
        return accountinfoMapper.getAllAccountinfo();
    }
}
