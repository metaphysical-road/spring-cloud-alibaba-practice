package com.alibaba.cloud.youxia.service.impl;
import com.alibaba.cloud.youxia.bo.Example2ProductBo;
import com.alibaba.cloud.youxia.config.SyncOrAsyncConfig;
import com.alibaba.cloud.youxia.dto.GoodDTO;
import com.alibaba.cloud.youxia.entity.Example2ProductEntity;
import com.alibaba.cloud.youxia.mapper.Example2ProductMapper;
import com.alibaba.cloud.youxia.request.GoodServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;
import com.alibaba.cloud.youxia.service.GoodService;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @author huxian
 */
@DubboService(version = "1.0.0",group = "use-redisson-distribute-lock-spring-cloud-alibaba",timeout = 3000,executes=20,actives = 20,connections = 20)
public class GoodServiceImpl implements GoodService {
    @Autowired
    private Example2ProductMapper example2ProductMapper;
    @Resource
    @Qualifier(value = "goodRLock")
    private RLock rlock;
    @Resource
    private SyncOrAsyncConfig syncOrAsyncConfig;

    @Override
    public DefaultResult<GoodDTO> updateGoodNum(GoodServiceRequest goodServiceRequest) {
        DefaultResult<GoodDTO> result = new DefaultResult<>();
        final GoodDTO returnItem = new GoodDTO();
        final Example2ProductBo example2ProductBo = (Example2ProductBo) goodServiceRequest.getRequestData();
        if (syncOrAsyncConfig.getUse()==1) {
            Thread thread = Thread.currentThread();
            final long threadId = thread.getId();
            System.out.println("加同步分布式锁，开始扣减库存：" + example2ProductBo.toString() + "扣减库处理请求的线程ID为：" + threadId + " 当前请求的" +
                    "uuid为：" + goodServiceRequest.getUuid() + " 扣减库存源请求的线程ID为：" + goodServiceRequest.getThreadId() + " 执行扣减库存的时间：" + new Date().toString());
            if (syncOrAsyncConfig.getType().equals("sync")) {
                try {
                    boolean tryLockResult = rlock.tryLock(1L, 5L, TimeUnit.SECONDS);
                    if (tryLockResult) {
                        rlock.lock(5L, TimeUnit.SECONDS);
                        execute(result, returnItem, example2ProductBo);
                    }
                } catch (InterruptedException exception) {
                    result.setData(null);
                    result.setCode("500");
                    result.setMessage("加同步分布式锁，库存扣减失败！！！！！");
                    System.out.println(exception.getMessage());
                } catch (Exception e) {
                    result.setData(null);
                    result.setCode("500");
                    result.setMessage("加同步分布式是锁，库存扣减失败！！！！！");
                    System.out.println(e.getMessage());
                } finally {
                    if (rlock.isLocked()) {
                        rlock.unlock();
                    }
                }
            } else if (syncOrAsyncConfig.getType().equals("async")) {
                try {
                    System.out.println("加异步分布式锁，开始扣减库存：" + example2ProductBo.toString() + "扣减库处理请求的线程ID为：" + threadId + " 当前请求的" +
                            "uuid为：" + goodServiceRequest.getUuid() + " 扣减库存源请求的线程ID为：" + goodServiceRequest.getThreadId() + " 执行扣减库存的时间：" + new Date().toString());
                    RFuture<Boolean> rFuture = rlock.tryLockAsync(1, 5, TimeUnit.SECONDS, threadId);
                    if (rFuture.isSuccess()) {
                        rlock.lockAsync(5, TimeUnit.SECONDS, threadId);
                        execute(result, returnItem, example2ProductBo);
                    }
                } catch (Exception e) {
                    result.setData(null);
                    result.setCode("500");
                    result.setMessage("加异步分布式锁，库存扣减失败！！！！！");
                    System.out.println(e.getMessage());
                } finally {
                    if (rlock.isLocked()) {
                        rlock.unlockAsync();
                    }
                }
            }
        } else {
            try {
                Thread thread = Thread.currentThread();
                final long threadId = thread.getId();
                System.out.println("不加分布式锁，开始扣减库存：" + example2ProductBo.toString() + "扣减库处理请求的线程ID为：" + threadId + " 当前请求的" +
                        "uuid为：" + goodServiceRequest.getUuid() + " 扣减库存源请求的线程ID为：" + goodServiceRequest.getThreadId() + " 执行扣减库存的时间：" + new Date().toString());
                execute(result, returnItem, example2ProductBo);
            } catch (Exception e) {
                result.setData(null);
                result.setCode("500");
                result.setMessage("不加分布式锁库存扣减失败！！！！！");
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    private void execute(DefaultResult<GoodDTO> result, GoodDTO returnItem, Example2ProductBo example2ProductBo) {
        List<Example2ProductEntity> querySyncResult1 = example2ProductMapper.queryGoodInfoByGoodId(example2ProductBo);
        if (!CollectionUtils.isEmpty(querySyncResult1)) {
            Example2ProductEntity item = querySyncResult1.get(0);
            System.out.println("开始扣减库存，扣除之前的商品库存为：" + item.getNum() + " 商品ID为：" + item.getGoodId());
        }
        example2ProductMapper.updateGoodNum(example2ProductBo);
        List<Example2ProductEntity> queryResult2 = example2ProductMapper.queryGoodInfoByGoodId(example2ProductBo);
        if (!CollectionUtils.isEmpty(queryResult2)) {
            Example2ProductEntity item = queryResult2.get(0);
            System.out.println("开始扣减库存，扣除之后的商品库存为：" + item.getNum() + " 商品ID为：" + item.getGoodId());
        }
        returnItem.setGoodId(queryResult2.get(0).getGoodId());
        returnItem.setNum(queryResult2.get(0).getNum());
        result.setData(returnItem);
        result.setCode("200");
        result.setMessage("库存扣减成功！！！！！");
    }
}
