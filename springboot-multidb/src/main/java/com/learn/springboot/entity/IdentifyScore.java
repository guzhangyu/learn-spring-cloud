package com.learn.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 阈值设置
 * </p>
 *
 * @author zs
 * @since 2020-09-11
 */
@Data
@Builder
public class IdentifyScore extends Model<IdentifyScore> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 阈值识别分数
     */
    private Integer score;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "IdentifyScore{" +
        ", id=" + id +
        ", score=" + score +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
