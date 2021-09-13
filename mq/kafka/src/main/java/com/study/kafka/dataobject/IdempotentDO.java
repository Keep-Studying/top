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
 * @author boyan
 * @version : IdempotentDO.java, v 0.1 2021年07月12日 4:20 下午 boyan Exp $
 */
@Data
@TableName(value = "ams_idempotent")
public class IdempotentDO extends ToString {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long   id;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date   gmtCreate;
    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date   gmtModified;
    /**
     * 租户id
     */
    @TableField(value = "tnt_inst_id")
    private String tntInstId;
    /**
     * 分库分表键
     */
    @TableField(value = "db_split_key")
    private String dbSplitKey;
    /**
     * 幂等键
     */
    @TableField(value = "idempotent_key")
    private String idempotentKey;
    /**
     * 状态
     */
    @TableField(value = "status")
    private String status;
    /**
     * 关联业务编号
     */
    @TableField(value = "biz_no")
    private String bizNo;
}