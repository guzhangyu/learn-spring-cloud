package com.learn.springcloud.dao;

import com.learn.springcloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhangyugu
 * @Date 2020/8/15 11:47 上午
 * @Version 1.0
 */
@Mapper
public interface OrderMapper {

    void insert(Order order);

    List<Order> queryByTradeAndItem(@Param("tradeId") Long tradeId, @Param("itemId") Long itemId);
}
