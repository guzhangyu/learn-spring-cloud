package com.learn.springcloud.dao;

import com.learn.springcloud.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zhangyugu
 * @Date 2020/8/15 11:46 上午
 * @Version 1.0
 */
@Mapper
public interface AccountMapper {

    void updateScore(Account account);

    Account selectById(Long id);
}
