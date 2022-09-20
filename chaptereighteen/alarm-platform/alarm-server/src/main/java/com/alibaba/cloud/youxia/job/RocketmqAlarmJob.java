package com.alibaba.cloud.youxia.job;

import com.alibaba.cloud.youxia.config.NacosAlarmConfig;
import com.alibaba.cloud.youxia.config.RocketMQAlarmConfig;
import com.alibaba.cloud.youxia.config.RocketMQWhiteListConfig;
import com.alibaba.cloud.youxia.dto.QueueStatInfo;
import com.alibaba.cloud.youxia.dto.TopicConsumerInfo;
import com.alibaba.cloud.youxia.util.DingDingUtils;
import com.alibaba.cloud.youxia.util.HttpClientUtils;
import com.alibaba.cloud.youxia.util.RestResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RocketmqAlarmJob implements SimpleJob {

    @Autowired
    private NacosAlarmConfig nacosAlarmConfig;
    @Autowired
    private RocketMQAlarmConfig rocketMQAlarmConfig;
    @Autowired
    private RocketMQWhiteListConfig rocketMQWhiteListConfig;
    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            List<String> groupList = getGroupList();
            List<List<TopicConsumerInfo>> topicConsumerInfo = getTopicConsumerInfo(groupList);
            whiteList(topicConsumerInfo);
            sendAlarm(topicConsumerInfo);
        } catch (Exception e) {
            log.error("RocketMQ 告警失败。", e);
        }
    }

    public List<List<TopicConsumerInfo>> getTopicConsumerInfo(List<String> groupList) throws Exception {
        List<List<TopicConsumerInfo>> consumerGroup = new ArrayList<>();
        for (String group : groupList) {
            String groupUrl = rocketMQAlarmConfig.getGroupInfoUrl() + "=" + group;
            RestResult restResult = HttpClientUtils.get(groupUrl, null,"rocketmq");
            HttpClientUtils.checkHttpResult(restResult);
            JSONObject infosObject = JSONObject.parseObject(restResult.getMessage());
            JSONArray dataJson = new JSONArray();
            if (infosObject != null) {
                dataJson = infosObject.getJSONArray("data");
            }
            List<TopicConsumerInfo> topicConsumerInfos = JSONObject
                .parseArray(dataJson.toJSONString(), TopicConsumerInfo.class);
            consumerGroup.add(topicConsumerInfos);
        }
        return consumerGroup;
    }

    public List<String> getGroupList() throws Exception {
        String groupListUrl = rocketMQAlarmConfig.getGroupListUrl();
        RestResult restResult = HttpClientUtils.get(groupListUrl, null,"rocketmq");
        HttpClientUtils.checkHttpResult(restResult);
        JSONObject groupsJson = JSONObject.parseObject(restResult.getMessage());
        JSONArray dataListGson = new JSONArray();
        if (groupsJson != null) {
            dataListGson = groupsJson.getJSONArray("data");
        }
        List<String> groupList = new ArrayList<>();
        if(null!=dataListGson){
            for (Object json : dataListGson) {
                JSONObject groupJson = (JSONObject) json;
                int diffTotal = (Integer) groupJson.get("diffTotal");
                if (diffTotal >= Integer.parseInt(rocketMQAlarmConfig.getDelayTotal())) {
                    Object group = groupJson.get("group");
                    groupList.add((String) group);
                }
            }
        }
        return groupList;
    }

    private void whiteList(List<List<TopicConsumerInfo>> alarmMessageList) {
        Iterator<List<TopicConsumerInfo>> listIterator = alarmMessageList.iterator();
        while (listIterator.hasNext()) {
            List<TopicConsumerInfo> listNext = listIterator.next();
            listNext.removeIf(next -> rocketMQWhiteListConfig.getRocketMQWhiteList().contains(next.getTopic()));
            if (listNext.isEmpty()) {
                listIterator.remove();
            }
        }
    }

    public void sendAlarm(List<List<TopicConsumerInfo>> topicConsumerInfo) throws ApiException {
        if (topicConsumerInfo.isEmpty()) {
            return;
        }
        OapiRobotSendRequest markDownRequest = new OapiRobotSendRequest();
        String secret = rocketMQAlarmConfig.getSecret();
        String webHook = rocketMQAlarmConfig.getWebhook();
        DingTalkClient client = DingDingUtils.getClient(secret,webHook);
        markDownRequest.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("ROCKETMQ 告警");
        StringBuffer markDownText = new StringBuffer();
        topicConsumerInfo.forEach(info -> {
            StringBuilder consumerText = new StringBuilder();
            consumerText.append("### RocketMQ 告警 \n\n" + "### 所属平台 :" + nacosAlarmConfig.getPlatform() + "\n\n");
            info.forEach(topicInfo -> {
                String addText =
                    "### 主题 :" + topicInfo.getTopic() + "   延迟 :" + topicInfo.getDiffTotal() + "   最后消费时间 :"
                        + DateFormatUtils.format(topicInfo.getLastTimestamp(), "yyyy-MM-dd HH:mm:ss") + "\n\n";
                StringBuilder sb = new StringBuilder(addText);
                for (QueueStatInfo queueStatInfo : topicInfo.getQueueStatInfoList()) {
                    String queueInfo =
                        "#### broker :" + queueStatInfo.getBrokerName() + "   延迟 :" + (queueStatInfo.getBrokerOffset()
                            - queueStatInfo.getConsumerOffset()) + "   最后消费时间 :" + DateFormatUtils
                            .format(queueStatInfo.getLastTimestamp(), "yyyy-MM-dd HH:mm:ss") + "\n\n";
                    sb.append(queueInfo);
                }
                consumerText.append(sb);
            });
            consumerText.append("---" + "\n\n");
            //钉钉限制一条消息不能超过20000byte
            String mt = String.valueOf(markDownText);
            if (mt.getBytes().length + consumerText.toString().getBytes().length >= 20000 && StringUtils
                .isNotEmpty(mt)) {

                try {
                    sendErrorMessage(markDownRequest, markdown, mt, client);
                } catch (ApiException e) {
                    log.error("RocketMQ推送钉钉失败", e);
                }
                markDownText.delete(0, markDownText.length());
            }
            markDownText.append(consumerText);
        });
        //发送告警信息
        sendErrorMessage(markDownRequest, markdown, String.valueOf(markDownText), client);
    }

    private void sendErrorMessage(OapiRobotSendRequest markDownRequest, OapiRobotSendRequest.Markdown markdown,
        String markDownText, DingTalkClient client) throws ApiException {
        log.info("RocketMq 告警信息{}",markDownText);
        markdown.setText(markDownText);
        markDownRequest.setMarkdown(markdown);
        OapiRobotSendResponse response = client.execute(markDownRequest);
        if (response != null && StringUtils.isNotBlank(response.getErrmsg())) {
            log.info("RocketMQ推送钉钉执行结果 execute:{}" + response.getErrmsg());
        }
    }
}
