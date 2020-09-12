package com.learn.springboot.config;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.learn.springboot.datasource.DynamicRoutingDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 6:13 下午
 * @Version 1.0
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@MapperScan(basePackages = {"com.learn.springboot.mapper"})
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicRoutingDataSource dynamicRoutingDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicRoutingDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("mybatisSqlSessionFactoryBean")
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DynamicRoutingDataSource dynamicRoutingDataSource) throws IOException {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml"));
        bean.setDataSource(dynamicRoutingDataSource);
        bean.setVfs(SpringBootVFS.class);

        com.baomidou.mybatisplus.core.MybatisConfiguration mybatisConfiguration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        mybatisConfiguration.setLogImpl(StdOutImpl.class);
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(mybatisConfiguration);

        bean.setGlobalConfig(GlobalConfigUtils.defaults()); //.setMetaObjectHandler(new MyMetaObjectHandler())
        return bean;
    }
}
