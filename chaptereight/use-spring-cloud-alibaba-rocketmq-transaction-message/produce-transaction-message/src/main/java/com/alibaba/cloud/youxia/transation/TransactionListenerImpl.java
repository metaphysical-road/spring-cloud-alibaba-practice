package com.alibaba.cloud.youxia.transation;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionListenerImpl implements TransactionListener {
    //原子更新
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    //本地事务执行结果缓存
    private ConcurrentHashMap<String, Integer> localTransactionCache = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        //取模运算，模拟本地事务的三个状态
        int status = value % 3;
        localTransactionCache.put(msg.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        //从本地事务执行结果缓存中获取对应事务消息执行的结果
        Integer status = localTransactionCache.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    //状态为0返回LocalTransactionState.UNKNOW
                    return LocalTransactionState.UNKNOW;
                case 1:
                    //状态为1返回LocalTransactionState.COMMIT_MESSAGE，提交事务消息
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    //状态为2返回LocalTransactionState.ROLLBACK_MESSAGE，回滚事务消息
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    //默认返回LocalTransactionState.COMMIT_MESSAGE
                    return LocalTransactionState.COMMIT_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
