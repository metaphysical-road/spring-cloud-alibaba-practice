package com.alibaba.cloud.youxia.config;

import com.alibaba.cloud.youxia.dto.AlarmPrincipalDTO;
import com.alibaba.cloud.youxia.dto.SkyOapConfigDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author huxian
 */
@Configuration
@Data
public class AlarmConfigService {
    @Resource
    private AlarmConfig alarmConfig;

    public boolean isWhiteService(String name) {
        boolean result = false;
        List<String> whiteServiceList = alarmConfig.getWhiteServiceList();
        for (String serviceName : whiteServiceList) {
            if (name.contains(serviceName)) {
                result = true;
                break;
            }
        }
        return result;
    }
    public String getServicePrincipalNum(String serviceName) {
        List<AlarmPrincipalDTO> principalConfigList = new Gson().fromJson(alarmConfig.getPrincipalConfigs(), new TypeToken<List<AlarmPrincipalDTO>>() {
        }.getType());
        String result = null;
        for (AlarmPrincipalDTO principalDTO : principalConfigList) {
            if (serviceName.contains(principalDTO.getServiceName())) {
                result = principalDTO.getPrincipalPhoneNum();
            }
        }
        return result;
    }
    public String getPlatformByIp(String ipAddr) {
        List<SkyOapConfigDTO> oapConfigs = new Gson().fromJson(alarmConfig.getSkyOapConfigs(), new TypeToken<List<SkyOapConfigDTO>>() {
        }.getType());
        String result="";
        for (SkyOapConfigDTO oapConfig : oapConfigs) {
            if (oapConfig.getIpConfigList().contains(ipAddr)) {
                result = oapConfig.getPlatform();
            }
        }
        return result;
    }
}
