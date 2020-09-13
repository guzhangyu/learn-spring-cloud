package com.learn.springboot.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zs
 * @Date: 2018/8/15 10:47
 * @Description: 动态数据源路由配置
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    //把当前事物下的连接塞入,用于事物处理
     ThreadLocal<Map<String, ConnectWrap>> connectionThreadLocal = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        log.info("当前数据源是：{}", dataSourceName);
        return DynamicDataSourceContextHolder.getDataSourceRouterKey();
    }

    /**
     * 开启事务的时候,把连接放入 线程中,后续crud 都会拿对应的连接操作
     *
     * @param key
     * @param connection
     */
    public void bindConnection(String key, Connection connection) {
        Map<String, ConnectWrap> connectionMap = connectionThreadLocal.get();
        if (connectionMap == null) {
            connectionMap = new HashMap<>();
            connectionThreadLocal.set(connectionMap);
        }

        //包装一下 不然给 spring把我关闭了
        ConnectWrap connectWarp = new ConnectWrap(connection);

        connectionMap.put(key, connectWarp);
    }


    /**
     * 提交事物
     *
     * @throws SQLException
     */
    public void doCommit() throws SQLException {
        System.out.println("0000011111111111111111111111111111000000000000000000");
        System.out.println(connectionThreadLocal.get().toString());
        Map<String, ConnectWrap> stringConnectionMap = connectionThreadLocal.get();
        if (stringConnectionMap == null) {
            return;
        }
        for (String dataSourceName : stringConnectionMap.keySet()) {
            ConnectWrap connection = stringConnectionMap.get(dataSourceName);
            connection.commit(true);
            connection.close(true);
        }
        removeConnectionThreadLocal();
    }

    /**
     * 撤销事物
     *
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        Map<String, ConnectWrap> stringConnectionMap = connectionThreadLocal.get();
        if (stringConnectionMap == null) {
            return;
        }
        for (String dataSourceName : stringConnectionMap.keySet()) {
            ConnectWrap connection = stringConnectionMap.get(dataSourceName);
            connection.rollback();
            connection.close(true);
        }
        removeConnectionThreadLocal();
    }

    public void removeConnectionThreadLocal() {
        connectionThreadLocal.remove();
    }


    /**
     * 如果 在connectionThreadLocal 中有 说明开启了事物,就从这里面拿
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        Map<String, ConnectWrap> stringConnectionMap = connectionThreadLocal.get();
        if (stringConnectionMap == null) {
            //没开事物 直接走
            return determineTargetDataSource().getConnection();
        } else {
            //开了事物,从当前线程中拿,而且拿到的是 包装过的connect 只有我能关闭O__O "…
            String currentName = (String) determineCurrentLookupKey();
            return stringConnectionMap.get(currentName);
        }

    }
}
