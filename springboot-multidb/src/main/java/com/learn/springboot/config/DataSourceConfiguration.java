package com.learn.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.learn.springboot.datasource.DynamicRoutingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 5:49 下午
 * @Version 1.0
 */
@Configuration
//@AutoConfigureAfter()
public class DataSourceConfiguration {

//    @Value("${spring.datasource.druid.defaultDs}")
//    private String defaultDs;

//    @Bean("dataSourceMaster")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
//    public DataSource dataSourceMaster() {
//        DataSource druidDataSource = DruidDataSourceBuilder.create().build();
////        DbContextHolder.addDataSource("master", druidDataSource);
//        return druidDataSource;
//    }
//
//    @Bean("dataSourceSlave")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
//    public DataSource dataSourceSlave() {
//        DataSource druidDataSource = DruidDataSourceBuilder.create().build();
////        DbContextHolder.addDataSource("slave", druidDataSource);
//        return druidDataSource;
//    }

//    @Bean("dynamicRoutingDataSource")
//    public DynamicRoutingDataSource dataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster, @Qualifier("dataSourceSlave") DataSource dataSourceSlave) {
//        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put("master", dataSourceMaster);
//        targetDataSources.put("slave", dataSourceSlave);
//
//        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceMaster);
//        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
////        DbContextHolder.setDefaultDs(defaultDs);
//        return dynamicRoutingDataSource;
//    }

//    @Bean(name = "transactionManager")
//    public DataSourceTransactionManager transactionManager(DynamicRoutingDataSource dynamicRoutingDataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dynamicRoutingDataSource);
//        return transactionManager;
//    }

}
