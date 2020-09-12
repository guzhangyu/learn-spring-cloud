package com.learn.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.springboot.annotation.DataSource;
import com.learn.springboot.entity.Student;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 7:19 下午
 * @Version 1.0
 */
@DataSource("master")
public interface StudentMapper extends BaseMapper<Student> {
}
