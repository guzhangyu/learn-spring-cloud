//package com.learn.springboot.config;
//
//import com.learn.springboot.annotation.DataSource;
//import com.learn.springboot.datasource.DynamicDataSourceContextHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * @Author zhangyugu
// * @Date 2020/9/12 6:32 下午
// * @Version 1.0
// */
//@Aspect
//@Component
//@Slf4j
//public class DsAspect {
//
//    @Pointcut("@within(com.learn.springboot.annotation.DataSource) || @annotation(com.learn.springboot.annotation.DataSource)")
//    public void pointCut(){
//
//    }
//
//    @Before("pointCut() && @annotation(ds)")
//    public void changeDataSource(DataSource ds) throws Throwable {
//        String dsId = ds.value();
//        log.info("选择数据源:{}", dsId);
//        DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
////        DbContextHolder.setCurrentDsStr(dsId);
//    }
//
//    @After("pointCut()")
//    public void restoreDataSource() {
////        DbContextHolder.clearCurrentDsStr();
//        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
//    }
//}
