package com.learn.springcloud.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author zhangyugu
 * @Date 2020/8/15 11:39 上午
 * @Version 1.0
 */
@Data
@ToString
public class Account {

    private Long id;

    private String name;

    private Integer score;

    private Date gmtCreate;

    private Date gmtModify;
}
