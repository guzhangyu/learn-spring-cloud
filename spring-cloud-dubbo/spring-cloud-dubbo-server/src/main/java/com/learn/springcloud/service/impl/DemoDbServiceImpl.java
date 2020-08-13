package com.learn.springcloud.service.impl;

import com.learn.springcloud.service.DemoDbService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 8:06 上午
 * @Version 1.0
 */
@DubboService
public class DemoDbServiceImpl implements DemoDbService {

    @Override
    public String hello(String name) {
        return "haha " + name;
    }
}
