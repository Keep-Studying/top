/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.dataobject;

import java.util.Date;
import java.util.List;
import com.study.kafka.domain.ToString;
import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

/**
 * 数据集DO
 *
 * @author boyan
 * @version : DataSetDO.java, v 0.1 2021年07月06日 7:19 下午 boyan Exp $
 */
@Data
@TableName(value = "ams_dataset")
public class DataSetDO extends ToString {
    private static final long               serialVersionUID = -3311721976646937833L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private              Long               id;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private              Date               gmtCreate;
    /**
     * 创建者
     */
    @TableField(value = "create_operator", fill = FieldFill.INSERT)
    private              String             createOperator;
    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private              Date               gmtModified;
    /**
     * 修改者
     */
    @TableField(value = "modified_operator", fill = FieldFill.INSERT_UPDATE)
    private              String             modifiedOperator;
    /**
     * 数据集UK(dataset_id+version)
     */
    @TableField(value = "dataset_uk")
    private              String             datasetUk;
    /**
     * 数据集ID
     */
    @TableField(value = "dataset_id")
    private              String             datasetId;
    /**
     * 名字
     */
    @TableField(value = "name")
    private              String             name;
    /**
     * 版本
     */
    @TableField(value = "version")
    private              String             version;
    /**
     * 门户
     */
    @TableField(value = "portal")
    private              String             portal;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    private              String             remarks;
    /**
     * 连接json
     */
    @TableField(value = "connections")
    private              String             connections;
    /**
     * 列和认证的关系json
     */
    @TableField(value = "data_auths")
    private              String             dataAuths;
    /**
     * 列集合
     */
    @TableField(exist = false)
    private              List<DataColumnDO> columns;
    /**
     * 表集合
     */
    @TableField(exist = false)
    private              List<DataTableDO>  tables;
}