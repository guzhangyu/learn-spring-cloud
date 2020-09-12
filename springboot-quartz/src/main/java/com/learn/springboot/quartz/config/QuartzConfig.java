package com.learn.springboot.quartz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author zhangyugu
 * @Date 2020/8/21 11:36 上午
 * @Version 1.0
 */
@Configuration
public class QuartzConfig {

    @Primary
    @Bean("qz1DataSource")
    @Qualifier("qz1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.qz1")
    public DataSource qz1DataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    //--------------------------

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.qz")
    public DataSourceProperties qzDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("qzDataSource")
    @Qualifier("qzDataSource")
    public HikariDataSource qzDataSource() {
        return qzDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public PlatformTransactionManager quartzDataSourceTransactionManager(@Qualifier("qzDataSource") DataSource qzDataSource) {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(qzDataSource);
        return transactionManager;
    }

//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    @Bean
//    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(@Qualifier("qzDataSource") DataSource qzDataSource,
//                                                                         @Qualifier("quartzDataSourceTransactionManager") PlatformTransactionManager quartzDataSourceTransactionManager) {
//        return schedulerFactoryBean -> {
//            schedulerFactoryBean.setDataSource(qzDataSource);
//            schedulerFactoryBean.setTransactionManager(quartzDataSourceTransactionManager);
//        };
//    }


    @Bean
    public SchedulerFactoryBean schedulerFactory(@Qualifier("qzDataSource") DataSource qzDataSource) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setDataSource(qzDataSource);
        bean.setTransactionManager(quartzDataSourceTransactionManager(qzDataSource));
        return bean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactory(qzDataSource()).getScheduler();
    }

    //--------------------------


    @Bean(name = "quartzJdbcTemplate")
    public JdbcTemplate quartzJdbcTemplate(@Qualifier("qzDataSource") DataSource quartzDataSource) {
        return new JdbcTemplate(quartzDataSource);
    }
}
