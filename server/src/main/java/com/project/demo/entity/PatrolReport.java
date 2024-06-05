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
 * 巡查上报：(PatrolReport)表实体类
 *
 */
@TableName("`patrol_report`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PatrolReport implements Serializable {

    // PatrolReport编号
    @TableId(value = "patrol_report_id", type = IdType.AUTO)
    private Integer patrol_report_id;

    // 巡查人员
    @TableField(value = "`patrol_personnel`")
    private Integer patrol_personnel;
    // 人员姓名
    @TableField(value = "`personnel_name`")
    private String personnel_name;
    // 上报标题
    @TableField(value = "`report_title`")
    private String report_title;
    // 上报类型
    @TableField(value = "`report_type`")
    private String report_type;
    // 上报时间
    @TableField(value = "`reporting_time`")
    private Timestamp reporting_time;
    // 上报位置
    @TableField(value = "`reporting_location`")
    private String reporting_location;
    // 上报图片
    @TableField(value = "`submit_images`")
    private String submit_images;
    // 上报内容
    @TableField(value = "`report_content`")
    private String report_content;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
