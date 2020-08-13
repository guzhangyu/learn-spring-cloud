package com.learn.springcloud.service;

import java.io.Serializable;

/**
 * @Author zhangyugu
 * @Date 2020/8/13 3:45 下午
 * @Version 1.0
 */
public class MessageDTO implements Serializable {

    private Integer id;

    private String name;

    public MessageDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
