package com.alibaba.cloud.youxia.transation;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
@Component
public class ProducerTransationService {
    @Resource
    private TransactionMQProducer transactionMQProducer;

    public String sendMessage() throws MQClientException, InterruptedException{
        String[] tags = new String[]{"订单", "商品", "支付", "货品", "物流","仓库"};
        SendResult sendResult=null;
        for (int i = 0; i < 200; i++) {
            try {
                Message msg =
                        new Message("use-spring-cloud-alibaba-rocketmq-transaction-message", tags[i % tags.length], "KEY" + i,
                                ("事务测试消息" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                sendResult = transactionMQProducer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);
                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
        }
        return sendResult.toString();
    }
}
