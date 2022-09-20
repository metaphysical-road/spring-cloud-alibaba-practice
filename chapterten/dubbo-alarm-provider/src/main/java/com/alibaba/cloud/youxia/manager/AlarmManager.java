package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.config.NacosConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
@RefreshScope
public class AlarmManager {
    @Resource
    private NacosConfig nacosConfig;

    public String alarm() {
        if(nacosConfig.getOpen().equals("true")){
            String time=nacosConfig.getTime();
            try {
                Thread.sleep(Long.valueOf(time));
            }catch (InterruptedException interruptedException){
                System.out.println(interruptedException.getMessage());
            }
            return "error!";
        }else{
            return "sucdess!";
        }
    }
}
