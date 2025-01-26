package com.learn.springcloud.stream.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 2:12 下午
 * @Version 1.0
 */
@EnableBinding(DemoInput.class)
public class DemoReceiver {

    private static Logger logger = LoggerFactory.getLogger(DemoReceiver.class);

    @StreamListener(DemoInput.DEMO_INPUT)
    public void receive(Object payload) {
        logger.info("Received: " + payload);
    }
}

interface DemoInput {

    String DEMO_INPUT = "demo_input";

    @Input(DEMO_INPUT)
    SubscribableChannel input();
}
