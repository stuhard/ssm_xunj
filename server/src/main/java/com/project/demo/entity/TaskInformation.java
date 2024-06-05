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
 * 任务信息：(TaskInformation)表实体类
 *
 */
@TableName("`task_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class TaskInformation implements Serializable {

    // TaskInformation编号
    @TableId(value = "task_information_id", type = IdType.AUTO)
    private Integer task_information_id;

    // 巡查人员
    @TableField(value = "`patrol_personnel`")
    private Integer patrol_personnel;
    // 任务标题
    @TableField(value = "`task_title`")
    private String task_title;
    // 任务类型
    @TableField(value = "`task_type`")
    private String task_type;
    // 任务附件
    @TableField(value = "`task_attachment`")
    private String task_attachment;
    // 发布日期
    @TableField(value = "`release_date`")
    private Timestamp release_date;
    // 任务要求
    @TableField(value = "`task_requirements`")
    private String task_requirements;
    // 任务内容
    @TableField(value = "`task_content`")
    private String task_content;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
