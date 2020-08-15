package com.learn.springcloud.service;

import com.learn.springcloud.dao.AccountMapper;
import com.learn.springcloud.dao.OrderMapper;
import com.learn.springcloud.entity.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 3:45 下午
 * @Version 1.0
 */
@Service
public class RocketMqTestService {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Autowired
    OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        Order order = new Order();
        order.setTradeId(1L);
        order.setItemId(1L);
        order.setItemName("item1");
        order.setItemPrice(new BigDecimal("0.3"));
        order.setNum(4);
        order.setAccountId(1L);
        order.setGmtCreate(new Date());
        orderMapper.insert(order);

        // 事务id
        String transactionId = "trans-1";
        rocketMQTemplate.sendMessageInTransaction("erp",
                "update-account-score",
                MessageBuilder.withPayload(order)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .setHeader("share_id", 3).build(),
                4L
        );
        System.out.println(" prepare 消息发送成功");
        // 这里消息发送只是prepare发送，
        // 后面消息队列中prepare成功后，在TestTransactionListener中的executeLocalTransaction的方法中决定是否要提交本地事务
    }
}
