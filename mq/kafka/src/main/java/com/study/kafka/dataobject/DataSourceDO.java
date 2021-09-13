/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import com.study.kafka.domain.ToString;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 数据源DO
 *
 * @author boyan
 * @version : DataSourceDO.java, v 0.1 2021年07月06日 7:11 下午 boyan Exp $
 */
@Data
@TableName(value = "ams_datasource")
public class DataSourceDO extends ToString {

    private static final long              serialVersionUID = 2837792498871821012L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private              Long              id;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private              Date              gmtCreate;
    /**
     * 创建者
     */
    @TableField(value = "create_operator", fill = FieldFill.INSERT)
    private              String            createOperator;
    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private              Date              gmtModified;
    /**
     * 修改者
     */
    @TableField(value = "modified_operator", fill = FieldFill.INSERT_UPDATE)
    private              String            modifiedOperator;
    /**
     * 数据源ID
     */
    @TableField(value = "datasource_id")
    private              String            datasourceId;
    /**
     * 连接服务器(IP)
     */
    @TableField(value = "server")
    private              String            server;
    /**
     * 端口
     */
    @TableField(value = "port")
    private              String            port;
    /**
     * 项目名称
     */
    @TableField(value = "project")
    private              String            project;
    /**
     * 账户
     */
    @TableField(value = "user_name")
    private              String            userName;
    /**
     * 密码
     */
    @TableField(value = "password")
    private              String            password;
    /**
     * 名字
     */
    @TableField(value = "name")
    private              String            name;
    /**
     * 数据源类型(MYSQL/KYLIN/*)
     */
    @TableField(value = "type")
    private              String            type;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    private              String            remarks;
    /**
     * 关联的VIP对应的datasourceId
     */
    @TableField(value = "relation")
    private              String            relation;
    /**
     * 表集合
     */
    @TableField(exist = false)
    private              List<DataTableDO> tables;
}