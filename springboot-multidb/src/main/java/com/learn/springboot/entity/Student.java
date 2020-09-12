package com.learn.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author zhangyugu
 * @Date 2020/9/12 7:20 下午
 * @Version 1.0
 */
@Data
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    private String studentNub;

    /**
     * 学生名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 班级号
     */
    private String classNub;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级
     */
    private String grade;

    /**
     * 专业号
     */
    private String professionalNub;

    /**
     * 专业名称
     */
    private String professionalName;

    /**
     * 学制
     */
    private String schooling;

    /**
     * 院系号
     */
    private String facultyNub;

    /**
     * 院系名称
     */
    private String facultyName;

    /**
     * 校区名称
     */
    private String campusName;

    /**
     * 宿舍号
     */
    private String roomNub;

    /**
     * 床位号
     */
    private String bedNub;

    /**
     * 成员id
     */
    private Long memberId;

    /**
     * 工号
     */
    private String memberJobNum;

    /**
     * 成员工号
     */
    private String jobNum;

    /**
     * 人脸图片id
     */
    private Long studentFaceFileId;

    /**
     * 一卡通卡号
     */
    private String card;

    /**
     * 创建时间
     */
    private Date sCrtTime;

    /**
     * 更新时间
     */
    private Date sUpdTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0 不可用 1可用
     */
    private Integer vaild;

    /**
     * 外部人员id
     */
    private String outId;

    /**
     * 钉钉的userid
     */
    private String ddUserId;

    /**
     * 0默认进入 1出去
     */
    private Integer enter;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", id=" + id +
                ", studentNub=" + studentNub +
                ", name=" + name +
                ", sex=" + sex +
                ", idCard=" + idCard +
                ", mobile=" + mobile +
                ", classNub=" + classNub +
                ", className=" + className +
                ", grade=" + grade +
                ", professionalNub=" + professionalNub +
                ", professionalName=" + professionalName +
                ", schooling=" + schooling +
                ", facultyNub=" + facultyNub +
                ", facultyName=" + facultyName +
                ", campusName=" + campusName +
                ", roomNub=" + roomNub +
                ", bedNub=" + bedNub +
                ", memberId=" + memberId +
                ", memberJobNum=" + memberJobNum +
                ", jobNum=" + jobNum +
                ", studentFaceFileId=" + studentFaceFileId +
                ", card=" + card +
                ", sCrtTime=" + sCrtTime +
                ", sUpdTime=" + sUpdTime +
                ", remark=" + remark +
                ", vaild=" + vaild +
                ", outId=" + outId +
                ", ddUserId=" + ddUserId +
                ", enter=" + enter +
                "}";
    }
}
