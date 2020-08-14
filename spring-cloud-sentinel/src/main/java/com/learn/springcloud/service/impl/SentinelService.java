package com.learn.springcloud.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author zhangyugu
 * @Date 2020/8/14 1:29 下午
 * @Version 1.0
 */
@Service
public class SentinelService {

    Logger log = LoggerFactory.getLogger(SentinelService.class);

    @SentinelResource(value = "doSomeThing", blockHandler = "blockHandler", fallback = "fallbackHandler")
    public void doSomeThing(String str) {
        if(new Random().nextInt(10) > 4) {
            throw new RuntimeException("随机异常");
        }
        log.info(str);
    }

    /**
     * 阻塞时的处理
     * @param str
     * @param ex
     */
    public void blockHandler(String str, BlockException ex) {
        log.error("blockHandler: " + str, ex);
    }

    /**
     * 降级处理的方法
     * @param str
     */
    public void fallbackHandler(String str) {
        log.error("fallbackHandler: " + str);
    }
}
