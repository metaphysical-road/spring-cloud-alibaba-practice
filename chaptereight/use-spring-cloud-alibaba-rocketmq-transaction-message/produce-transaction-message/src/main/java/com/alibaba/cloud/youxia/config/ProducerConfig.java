package com.alibaba.cloud.youxia.config;

import com.alibaba.cloud.youxia.transation.TransactionListenerImpl;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ProducerConfig {

    @Bean
    public TransactionMQProducer newTransactionMQProducer() throws MQClientException {
        TransactionListener transactionListener =new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("use-spring-cloud-alibaba-rocketmq-transaction-message");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("检查客户端事务状态的线程");
                return thread;
            }
        });
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();
        return producer;
    }
}
