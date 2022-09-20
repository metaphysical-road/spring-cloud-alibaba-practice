package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.bo.AccountBo;
import com.alibaba.cloud.youxia.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountManager {
    @Resource
    private AccountMapper accountMapper;

    public boolean deductAccountBalance(Long userId, BigDecimal amount,Long accountId){
        AccountBo accountBo=new AccountBo();
        accountBo.setAccountId(accountId);
        accountBo.setAmount(amount);
        accountBo.setGmtModified(new Date());
        accountBo.setUserId(userId);
        Integer result=accountMapper.deductAccountBalance(accountBo);
        return result>0?true:false;
    }
}
