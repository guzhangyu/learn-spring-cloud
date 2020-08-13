package com.learn.springcloud.dao;

import com.learn.springcloud.entity.Websites;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebsitesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Websites record);

    Websites selectByPrimaryKey(Integer id);

    List<Websites> selectAll();

    int updateByPrimaryKey(Websites record);
}