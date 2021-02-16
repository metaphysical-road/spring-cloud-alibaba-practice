package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.dao.AccountinfoDAO;
import com.alibaba.cloud.youxia.dao.AccountinfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountinfoService {

    @Autowired
    private AccountinfoDAO accountinfoDAO;

    public List<AccountinfoDO> getAccountinfo(){
        return accountinfoDAO.getAllAccountinfo();
    }
}
