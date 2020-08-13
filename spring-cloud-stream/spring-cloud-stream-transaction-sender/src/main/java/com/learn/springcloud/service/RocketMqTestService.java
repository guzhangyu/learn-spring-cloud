package com.learn.springcloud.service;

import com.learn.springcloud.dao.WebsitesMapper;
import com.learn.springcloud.entity.Websites;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    WebsitesMapper websitesMapper;

    @Transactional
    public void testTransaction() {
        Websites websites = new Websites();
        websites.setAlexa(2);
        websites.setCountry("CN");
        websites.setName("谷章雨");
        websites.setUrl("http://www.baidu.com");
        websitesMapper.insert(websites);

        String transactionId = "trans-1"; // 事务id
        rocketMQTemplate.sendMessageInTransaction("test-group",
                "test1",
                MessageBuilder.withPayload(new MessageDTO(3, "first message"))
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .setHeader("share_id", 3).build(),
                websites
        );
        System.out.println(" prepare 消息发送成功");
        // 这里消息发送只是prepare发送，
        // 后面消息队列中prepare成功后，在TestTransactionListener中的executeLocalTransaction的方法中决定是否要提交本地事务
    }
}
