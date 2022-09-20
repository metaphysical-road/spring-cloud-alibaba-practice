package com.alibaba.cloud.youxia.executor;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class ProductorData {
    @Resource
    private DataExecutor dataExecutor;

    private volatile Boolean isfirst = false;

    class ExecutorThread implements Runnable {
        private DataExecutor dataExecutor;

        public ExecutorThread(DataExecutor dataExecutor) {
            this.dataExecutor = dataExecutor;
        }

        @Override
        public void run() {
            if (!isfirst) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @PostConstruct
    public void init() {
//        ExecutorService executorService= Executors.newFixedThreadPool(1);
//        executorService.execute(new ExecutorThread(dataExecutor));
    }
}
