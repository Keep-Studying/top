/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import com.study.kafka.domain.ToString;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据表DO
 *
 * @author boyan
 * @version : DataTableDO.java, v 0.1 2021年07月06日 7:25 下午 boyan Exp $
 */
@Data
@TableName(value = "ams_datatable")
@Accessors(chain = true)
public class DataTableDO extends ToString {
    private static final long               serialVersionUID = 8839293688619290314L;
    /**
     * 连接类型
     */
    @TableField(exist = false)
    protected String connectorType;
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
     * 表id
     */
    @TableField(value = "table_id")
    private              String             tableId;
    /**
     * 表目录
     */
    @TableField(value = "table_cat")
    private              String             tableCat;
    /**
     * 表模式
     */
    @TableField(value = "table_schema")
    private              String             tableSchema;
    /**
     * 表名
     */
    @TableField(value = "table_name")
    private              String             tableName;
    /**
     * 表类型
     */
    @TableField(value = "table_type")
    private              String             tableType;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    private              String             remarks;
    /**
     * 目录类型
     */
    @TableField(value = "type_cat")
    private              String             typeCat;
    /**
     * 模式类型
     */
    @TableField(value = "type_schema")
    private              String             typeSchema;
    /**
     * 表名类型
     */
    @TableField(value = "type_name")
    private              String             typeName;
    /**
     * 有类型表的指定identifier列的名称
     */
    @TableField(value = "self_referencing_col_name")
    private              String             selfReferencingColName;
    /**
     * 指定在self_referencing_col_name中创建值的方式
     */
    @TableField(value = "ref_generation")
    private              String             refGeneration;
    /**
     * 数据源ID
     */
    @TableField(value = "datasource_id")
    private              String             datasourceId;
    /**
     * 数据集id
     */
    @TableField(value = "dataset_id")
    private              Long               datasetId;
    /**
     * 表的行数
     */
    @TableField(value = "table_rows")
    private Long tableRows;
    /**
     * 数据长度
     */
    @TableField(value = "data_length")
    private Long dataLength;
    /**
     * 索引长度
     */
    @TableField(value = "index_length")
    private Long indexLength;
    /**
     * 自增值
     */
    @TableField(value = "auto_increment")
    private Long autoIncrement;
    /**
     * 引擎
     */
    @TableField(value = "engine")
    private String engine;
    /**
     * 表的字符序
     */
    @TableField(value = "table_collation")
    private String tableCollation;
    /**
     * 列集合
     */
    @TableField(exist = false)
    private              List<DataColumnDO> columns = new ArrayList<>();
    public void addColumn(DataColumnDO column) {
        this.columns.add(column);
    }

    public DataTableDO() {
    }

    public DataTableDO(String tableCat, String tableSchema, String tableName, String tableType, String remarks, String typeCat, String typeSchema, String typeName, String selfReferencingColName, String refGeneration) {
        this.tableCat = tableCat;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.tableType = tableType;
        this.remarks = remarks;
        this.typeCat = typeCat;
        this.typeSchema = typeSchema;
        this.typeName = typeName;
        this.selfReferencingColName = selfReferencingColName;
        this.refGeneration = refGeneration;
    }
}