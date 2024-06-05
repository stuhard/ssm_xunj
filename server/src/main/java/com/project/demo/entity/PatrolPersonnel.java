package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 巡查人员：(PatrolPersonnel)表实体类
 *
 */
@TableName("`patrol_personnel`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PatrolPersonnel implements Serializable {

    // PatrolPersonnel编号
    @TableId(value = "patrol_personnel_id", type = IdType.AUTO)
    private Integer patrol_personnel_id;

    // 人员姓名
    @TableField(value = "`personnel_name`")
    private String personnel_name;
    // 人员性别
    @TableField(value = "`gender_of_personnel`")
    private String gender_of_personnel;
    // 人员年龄
    @TableField(value = "`age_of_personnel`")
    private String age_of_personnel;







    // 用户编号
    @TableField(value = "user_id")
    private Integer userId;



    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
