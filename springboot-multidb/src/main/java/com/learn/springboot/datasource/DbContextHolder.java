//package com.learn.springboot.datasource;
//
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//import java.util.ArrayDeque;
//import java.util.Deque;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Author zhangyugu
// * @Date 2020/9/12 5:53 下午
// * @Version 1.0
// */
//public class DbContextHolder {
//
//    private static String defaultDs = "";
//
//    private static Map<String, DataSource> dataSources = new ConcurrentHashMap<>();
//
//    private static final ThreadLocal<Deque<String>> contextHolder = new ThreadLocal() {
//        @Override
//        protected Object initialValue() {
//            return new ArrayDeque();
//        }
//    };
//
//    public static void setCurrentDsStr(String dsName) {
//        if(StringUtils.isEmpty(dsName)) {
//            throw new NullPointerException();
//        }
//        if(!dataSources.containsKey(dsName)) {
//            throw new RuntimeException("dataSource not exists,dsName=" + dsName);
//        }
//        contextHolder.get().push(dsName);
//    }
//
//    public static String getCurrentDsStr() {
//        return contextHolder.get().peek();
//    }
//
//    public static void clearCurrentDsStr() {
//        Deque<String> deque = contextHolder.get();
//        deque.poll();
//        if(deque.isEmpty()) {
//            contextHolder.remove();
//        }
//    }
//
//    public static void addDataSource(String dsName, DataSource dataSource) {
//        dataSources.put(dsName, dataSource);
//    }
//
//    public static DataSource getDefaultDataSource() {
//        if(StringUtils.isEmpty(defaultDs)) {
//            throw new RuntimeException("default datasource must be configured.");
//        }
//
//        if(!dataSources.containsKey(defaultDs)) {
//            throw new RuntimeException("The default datasource must be included in the datasources.");
//        }
//        return dataSources.get(defaultDs);
//    }
//
//    public static void setDefaultDs(String defaultDs) {
//        DbContextHolder.defaultDs = defaultDs;
//    }
//
//    public static String getDefaultDs() {
//        return defaultDs;
//    }
//
//    public static Map<String, DataSource> getDataSources() {
//        return dataSources;
//    }
//}
