package com.alibaba.cloud.youxia.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

@Slf4j
public final class DingDingUtils {
    public static DingTalkClient getClient(final String secret,final String webHook) {
        DingTalkClient client = null;
        try {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = "&timestamp=" + timestamp + "&sign=" + URLEncoder
                    .encode(new String(Base64.encodeBase64(signData)), "UTF-8");
            client = new DefaultDingTalkClient(webHook + sign);
        } catch (Exception e) {
            log.error("fail to get ding talk client", e);
        }
        return client;
    }
}
