package com.learn.springboot.service;

import com.learn.springboot.entity.Student;
import com.learn.springboot.mapper.IdentifyScoreMapper;
import com.learn.springboot.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 7:18 下午
 * @Version 1.0
 */
@Service
public class TestTransactionService {

    @Resource
    StudentMapper studentMapper;

    @Resource
    IdentifyScoreMapper identifyScoreMapper;

    public void test() {
        identifyScoreMapper.updateScore(77);
        Student student = new Student();
        student.setId(1L);
        student.setName("test");
        studentMapper.updateById(student);

        student = new Student();
        student.setId(2L);
        student.setName("test");
        studentMapper.updateById(student);
    }
}
