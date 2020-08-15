package com.learn.springcloud.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangyugu
 * @Date 2020/8/15 11:39 上午
 * @Version 1.0
 */
@Data
@ToString
public class Order {

    private Long id;

    private Long tradeId;

    private Long itemId;

    private String itemName;

    private BigDecimal itemPrice;

    private Integer num;

    private Long accountId;

    private Date gmtCreate;

    private Date gmtModify;
}
