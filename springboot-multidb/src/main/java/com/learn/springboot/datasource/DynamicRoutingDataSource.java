package com.learn.springboot.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 5:53 下午
 * @Version 1.0
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
//        return DbContextHolder.getCurrentDsStr();
        return DynamicDataSourceContextHolder.getDataSourceRouterKey();
    }
}
