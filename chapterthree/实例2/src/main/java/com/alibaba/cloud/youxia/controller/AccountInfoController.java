package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.dao.AccountinfoDO;
import com.alibaba.cloud.youxia.service.AccountinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountInfoController {

    @Autowired
    private AccountinfoService accountinfoService;
    @GetMapping("/getAllAccountInfo")
    public String getAllAccountInfo(){
        List<AccountinfoDO> accountinfoDOList=accountinfoService.getAccountinfo();
        return accountinfoDOList.toString();
    }
}
