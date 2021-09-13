/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import com.study.kafka.domain.ToString;
import lombok.Data;

import java.util.Date;

/**
 * 看板DO
 *
 * @author boyan
 * @version : DataColumnDO.java, v 0.1 2021年07月06日 7:30 下午 boyan Exp $
 */
@Data
@TableName(value = "ams_board")
public class BoardDO extends ToString {

    private static final long    serialVersionUID = 1536662807472224779L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private              Long    id;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private              Date    gmtCreate;
    /**
     * 创建者
     */
    @TableField(value = "create_operator", fill = FieldFill.INSERT)
    private              String  createOperator;
    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private              Date    gmtModified;
    /**
     * 修改者
     */
    @TableField(value = "modified_operator", fill = FieldFill.INSERT_UPDATE)
    private              String  modifiedOperator;
    /**
     * 看板ID
     */
    @TableField(value = "board_id")
    private              String  boardId;
    /**
     * 看板名称
     */
    @TableField(value = "name")
    private              String  name;
    /**
     * 类型RPT(报告)/TWB(工作簿)
     */
    @TableField(value = "type")
    private              String  type;
    /**
     * 工作簿名称
     */
    @TableField(value = "twb_name")
    private              String  twbName;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    private              String  remarks;
    /**
     * 渠道(bix/tableau/*)
     */
    @TableField(value = "channel")
    private              String  channel;
    /**
     * 门户(火眼/智策/*)
     */
    @TableField(value = "portal")
    private              String  portal;
    /**
     * 优先级(1~10,数字越大,优先级越高)
     */
    @TableField(value = "priority")
    private              Integer priority;
    /**
     * 数据集ID的JSON串
     */
    @TableField(value = "dataset_ids")
    private              String  datasetIds;

}