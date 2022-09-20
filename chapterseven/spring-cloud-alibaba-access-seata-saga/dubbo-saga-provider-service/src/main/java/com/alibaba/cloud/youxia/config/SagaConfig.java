package com.alibaba.cloud.youxia.config;

import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.engine.config.DbStateMachineConfig;
import io.seata.saga.engine.impl.ProcessCtrlStateMachineEngine;
import io.seata.saga.rm.StateMachineEngineHolder;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.*;

@Configuration
public class SagaConfig{

    @Bean
    public DataSource getDatasource(){
        JdbcDataSource dataSource=new JdbcDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        dataSource.setURL("jdbc:h2:mem:SEATA_SAGA;INIT=RUNSCRIPT FROM 'classpath:sql/h2_init.sql'");
        return dataSource;
    }

    @Bean
    public ThreadPoolExecutor threadPoolExecutorFactoryBean(){
        ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(5,5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        return threadPoolExecutor;
    }

    @Bean
    public DbStateMachineConfig dbStateMachineConfig(DataSource dataSource, ThreadPoolExecutor threadPoolExecutor){
        DbStateMachineConfig dbStateMachineConfig=new DbStateMachineConfig();
        dbStateMachineConfig.setDataSource(dataSource);
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources=null;
        try{
           resources = patternResolver.getResources("classpath:statelang/saga-spring-cloud-alibaba.json");
        }catch (IOException e){
        }
        dbStateMachineConfig.setResources(resources);
        dbStateMachineConfig.setEnableAsync(true);
        dbStateMachineConfig.setThreadPoolExecutor(threadPoolExecutor);
        dbStateMachineConfig.setApplicationId("dubbo-saga-provider-service");
        dbStateMachineConfig.setTxServiceGroup("dubbo-saga-provider-service-tx-group");
        return dbStateMachineConfig;
    }

    @Bean
    public StateMachineEngine process(DbStateMachineConfig dbStateMachineConfig){
        ProcessCtrlStateMachineEngine processCtrlStateMachineEngine=new ProcessCtrlStateMachineEngine();
        processCtrlStateMachineEngine.setStateMachineConfig(dbStateMachineConfig);
        return processCtrlStateMachineEngine;
    }

    @Bean
    public StateMachineEngineHolder stateMachineEngineHolder(StateMachineEngine stateMachineEngine){
        StateMachineEngineHolder stateMachineEngineHolder=new StateMachineEngineHolder();
        stateMachineEngineHolder.setStateMachineEngine(stateMachineEngine);
        return stateMachineEngineHolder;
    }
}
