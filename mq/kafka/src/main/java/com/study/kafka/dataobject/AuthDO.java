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
 * 认证DO
 *
 * @author boyan
 * @version : AuthDO.java, v 0.1 2021年07月10日 10:08 上午 boyan Exp $
 */
@Data
@TableName(value = "ams_auth")
public class AuthDO extends ToString {
    private static final long serialVersionUID = -2185344354819177750L;

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
     * 创建者
     */
    @TableField(value = "create_operator", fill = FieldFill.INSERT)
    private String createOperator;
    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date   gmtModified;
    /**
     * 修改者
     */
    @TableField(value = "modified_operator", fill = FieldFill.INSERT_UPDATE)
    private String modifiedOperator;
    /**
     * 认证ID
     */
    @TableField(value = "auth_id")
    private String authId;
    /**
     * 门户
     */
    @TableField(value = "portal")
    private String portal;
    /**
     * 规则
     */
    @TableField(value = "rule")
    private String rule;
    /**
     * 角色集合(JSON串)
     */
    @TableField(value = "roles")
    private String roles;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;
}