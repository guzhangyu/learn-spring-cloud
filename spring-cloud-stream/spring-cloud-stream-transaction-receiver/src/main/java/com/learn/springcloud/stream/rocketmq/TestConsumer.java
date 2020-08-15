package com.learn.springcloud.stream.rocketmq;

import com.learn.springcloud.dao.AccountMapper;
import com.learn.springcloud.entity.Account;
import com.learn.springcloud.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 4:21 下午
 * @Version 1.0
 */
@EnableBinding(TestInput.class)
@Slf4j
public class TestConsumer {

    @Autowired
    AccountMapper accountMapper;


    @StreamListener(TestInput.TEST_INPUT)
    @Transactional(rollbackFor = Exception.class)
    public void input(Order order) {
//        throw new IllegalArgumentException("测试失败");

        Account account = accountMapper.selectById(order.getAccountId());
        if(account == null){
            throw new IllegalArgumentException("该用户不存在: " + order.getAccountId());
        }

        account.setScore(account.getScore() + 1);
        accountMapper.updateScore(account);
    }

    @StreamListener(TestInput.TEST_DLQ_INPUT)
    @Transactional(rollbackFor = Exception.class)
    public void dlqInput(Order order) {
//        throw new IllegalArgumentException("dlq测试失败");
        log.error("update-account-score失败:{}", order);
    }
}

interface TestInput {
    String TEST_INPUT = "input";

    String TEST_DLQ_INPUT = "inputDlq";

    @Input(TEST_INPUT)
    SubscribableChannel input();

    @Input(TEST_DLQ_INPUT)
    SubscribableChannel inputDlq();
}
