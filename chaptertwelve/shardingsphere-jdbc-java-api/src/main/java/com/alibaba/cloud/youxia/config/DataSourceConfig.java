package com.alibaba.cloud.youxia.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource init(){
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        HikariDataSource dataSource0 = new HikariDataSource();
        dataSource0.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource0.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/datasource_0");
        dataSource0.setUsername("root");
        dataSource0.setPassword("123456huxian");
        dataSourceMap.put("datasource_0", dataSource0);
        // 配置第 2 个数据源
        HikariDataSource datasource_1 = new HikariDataSource();
        datasource_1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource_1.setJdbcUrl("jdbc:mysql://localhost:3306/datasource_1");
        datasource_1.setUsername("root");
        datasource_1.setPassword("123456huxian");
        dataSourceMap.put("datasource_1", datasource_1);
        // 配置第 3 个数据源
        HikariDataSource datasource_2 = new HikariDataSource();
        datasource_2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource_2.setJdbcUrl("jdbc:mysql://localhost:3306/datasource_2");
        datasource_2.setUsername("root");
        datasource_2.setPassword("123456huxian");
        dataSourceMap.put("datasource_2", datasource_2);
        // 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new
                ShardingTableRuleConfiguration("t_order", "datasource_${0..2}.t_order");
        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration("order_id", "dbShardingAlgorithm"));
        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(
                new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);
        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "datasource_${user_id % 3}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm",
                new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));
        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order${order_id % 3}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm",
                new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));
        // 创建 ShardingSphereDataSource
        DataSource dataSource=null;
        try{
            dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap,
                    Collections.singleton(shardingRuleConfig), new Properties());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return dataSource;
    }
}
