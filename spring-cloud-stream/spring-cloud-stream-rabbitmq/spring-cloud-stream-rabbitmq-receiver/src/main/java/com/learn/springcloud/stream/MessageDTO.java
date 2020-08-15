package com.learn.springcloud.stream;

import java.io.Serializable;

/**
 * @Author zhangyugu
 * @Date 2020/8/14 4:08 下午
 * @Version 1.0
 */
public class MessageDTO implements Serializable {

    private Integer id;

    private String name;

    public MessageDTO() {
    }

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
