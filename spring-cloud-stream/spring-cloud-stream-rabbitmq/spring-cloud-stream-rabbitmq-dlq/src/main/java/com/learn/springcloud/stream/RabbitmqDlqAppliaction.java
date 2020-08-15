package com.learn.springcloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangyugu
 * @Date 2020/8/14 11:40 下午
 * @Version 1.0
 */
@SpringBootApplication
@EnableBinding(RabbitmqDlqAppliaction.GoodTopic.class)
public class RabbitmqDlqAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDlqAppliaction.class, args);
    }

    @RestController
    static class GoodController {

        @Autowired
        private GoodTopic goodTopic;

        @GetMapping("/sendMessage")
        public String messageWithMQ(@RequestParam String message) {
            goodTopic.output().send(MessageBuilder.withPayload(message).build());
            return "ok";
        }
    }

    @Component
    static class GoodListener {

        Logger logger = LoggerFactory.getLogger(GoodListener.class);

        @StreamListener(GoodTopic.INPUT)
        public void receive(String payload) {
            logger.info("Received payload : {}", payload);
            throw new RuntimeException("Message consumer failed!");
        }
    }

    interface GoodTopic {
        String OUTPUT = "example-topic-output";
        String INPUT = "example-topic-input";

        @Output(OUTPUT)
        MessageChannel output();

        @Input(INPUT)
        MessageChannel input();
    }
}
