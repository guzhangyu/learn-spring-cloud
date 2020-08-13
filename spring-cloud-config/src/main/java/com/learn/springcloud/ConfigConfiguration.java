package com.learn.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 7:26 上午
 * @Version 1.0
 */
@Component
public class ConfigConfiguration {

    @Value("${testProp}")
    private String testProp;

    public String getTestProp() {
        return testProp;
    }
}
