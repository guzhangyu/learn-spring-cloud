package com.learn.springcloud.stream.rocketmq;

import com.alibaba.fastjson.JSON;
import com.learn.springcloud.entity.Websites;
import com.learn.springcloud.service.MessageDTO;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 3:55 下午
 * @Version 1.0
 */
@RocketMQTransactionListener(txProducerGroup = "test-group")
public class TestTransactionListener implements RocketMQLocalTransactionListener {

    /**
     * rocketmq 消息发送成功之后，提交本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        MessageDTO messageDTO = JSON.parseObject(new String((byte[])message.getPayload()), MessageDTO.class);
        Websites websites = (Websites)o;
        System.out.println(String.format("half message\npayload:%s, arg:%s, transactionId:%s", messageDTO, websites, message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID)));
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * rocketmq 回查时，告诉它要提交，还是回滚
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // 根据message去查询本地事务是否执行成功，如果成功，则commit
        return RocketMQLocalTransactionState.COMMIT;
    }
}
