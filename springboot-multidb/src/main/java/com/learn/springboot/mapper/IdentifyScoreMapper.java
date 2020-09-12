package com.learn.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.springboot.annotation.DataSource;
import com.learn.springboot.entity.IdentifyScore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 阈值设置 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2020-09-11
 */
@DataSource("slave")
public interface IdentifyScoreMapper extends BaseMapper<IdentifyScore> {

    @DataSource("slave")
    @Update("update identify_score set score = #{score} where id = 1")
    void updateScore(@Param("score") Integer socre);
}
