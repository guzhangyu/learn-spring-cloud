package com.learn.springcloud.stream.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 4:21 下午
 * @Version 1.0
 */
@Component
@RocketMQMessageListener(consumerGroup = "test-group", topic = "test1")
public class TestConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }
}
