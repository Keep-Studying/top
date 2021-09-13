/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.dataobject;

import java.util.Date;
import com.study.kafka.domain.ToString;
import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

/**
 * 数据列DO
 *
 * @author boyan
 * @version : DataColumnDO.java, v 0.1 2021年07月06日 7:30 下午 boyan Exp $
 */
@Data
@TableName(value = "ams_datacolumn")
public class DataColumnDO extends ToString {
    private static final long    serialVersionUID = -7313244946609421595L;
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
     * 列ID
     */
    @TableField(value = "column_id")
    private              String  columnId;
    /**
     * 数据集依赖的类型名称
     */
    @TableField(value = "type")
    private              String  type;
    /**
     * 表目录
     */
    @TableField(value = "table_cat")
    private              String  tableCat;
    /**
     * 表模式
     */
    @TableField(value = "table_schema")
    private              String  tableSchema;
    /**
     * 表名
     */
    @TableField(value = "table_name")
    private              String  tableName;
    /**
     * 列名
     */
    @TableField(value = "column_name")
    private              String  columnName;
    /**
     * 数据类型(java.sql.Types的SQL类型)
     */
    @TableField(value = "data_type")
    private              Integer dataType;
    /**
     * 数据源依赖的类型名称
     */
    @TableField(value = "type_name")
    private              String  typeName;
    /**
     * 列的大小
     */
    @TableField(value = "column_size")
    private              Integer columnSize;
    /**
     * 缓冲长度(未使用)
     */
    @TableField(value = "buffer_length")
    private              Integer bufferLength;
    /**
     * 小数部分的位数
     */
    @TableField(value = "decimal_digits")
    private              Integer decimalDigits;
    /**
     * 基数(通常为10或2)
     */
    @TableField(value = "num_prec_radix")
    private              Integer numPrecRadix;
    /**
     * 是否允许使用NULL
     */
    @TableField(value = "nullable")
    private              Integer nullable;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    private              String  remarks;
    /**
     * 该列的默认值
     */
    @TableField(value = "column_def")
    private              String  columnDef;
    /**
     * sql的数据类型(未使用)
     */
    @TableField(value = "sql_data_type")
    private              Integer sqlDataType;
    /**
     * sql的日期(未使用)
     */
    @TableField(value = "sql_datetime_sub")
    private              Integer sqlDatetimeSub;
    /**
     * char类型时列中的最大字节数
     */
    @TableField(value = "char_octet_length")
    private              Integer charOctetLength;
    /**
     * 表中的列的索引(从1开始)
     */
    @TableField(value = "ordinal_position")
    private              Integer ordinalPosition;
    /**
     * ISO规则确定列是否包括null
     */
    @TableField(value = "is_nullable")
    private              String  isNullable;
    /**
     * 表类别的作用域
     */
    @TableField(value = "scope_catlog")
    private              String  scopeCatlog;
    /**
     * 表模式的作用域
     */
    @TableField(value = "scope_schema")
    private              String  scopeSchema;
    /**
     * 表的作用域
     */
    @TableField(value = "scope_table")
    private              String  scopeTable;
    /**
     * 不同类型或用户生成Ref类型
     */
    @TableField(value = "source_data_type")
    private              Integer sourceDataType;
    /**
     * 此列是否自动增加
     */
    @TableField(value = "is_autoincrement")
    private              String  isAutoincrement;
    /**
     * 此列是否为生成列
     */
    @TableField(value = "is_generatedcolumn")
    private              String  isGeneratedcolumn;
    /**
     * 数据源ID
     */
    @TableField(value = "datasource_id")
    private              String  datasourceId;
    /**
     * 数据集ID
     */
    @TableField(value = "dataset_id")
    private              Long    datasetId;
    /**
     * 数据表ID
     */
    @TableField(value = "datatable_id")
    private              Long    datatableId;
    /**
     * 权限
     */
    @TableField(value = "privileges")
    private String privileges;
    /**
     * 额外
     */
    @TableField(value = "extra")
    private String extra;
    /**
     * 字符集
     */
    @TableField(value = "character_set_name")
    private String characterSetName;
    /**
     * 排序规则
     */
    @TableField(value = "collation_name")
    private String collationName;
    /**
     * 字符最大长度
     */
    @TableField(value = "character_maximum_length")
    private Integer characterMaximumLength;
    /**
     * 指示此列是否自动增加：""
     */
    @TableField(value = "numeric_scale")
    private Integer numericScale;

    public DataColumnDO() {
    }

    public DataColumnDO(String tableCat, String tableSchema, String tableName, String columnName, Integer dataType, String typeName, Integer columnSize, Integer bufferLength, Integer decimalDigits, Integer numPrecRadix, Integer nullable, String remarks, String columnDef, Integer sqlDataType, Integer sqlDatetimeSub, Integer charOctetLength, Integer ordinalPosition, String isNullable, String scopeCatlog, String scopeSchema, String scopeTable, Integer sourceDataType, String isAutoincrement, String isGeneratedcolumn) {
        this.tableCat = tableCat;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.columnName = columnName;
        this.dataType = dataType;
        this.typeName = typeName;
        this.columnSize = columnSize;
        this.bufferLength = bufferLength;
        this.decimalDigits = decimalDigits;
        this.numPrecRadix = numPrecRadix;
        this.nullable = nullable;
        this.remarks = remarks;
        this.columnDef = columnDef;
        this.sqlDataType = sqlDataType;
        this.sqlDatetimeSub = sqlDatetimeSub;
        this.charOctetLength = charOctetLength;
        this.ordinalPosition = ordinalPosition;
        this.isNullable = isNullable;
        this.scopeCatlog = scopeCatlog;
        this.scopeSchema = scopeSchema;
        this.scopeTable = scopeTable;
        this.sourceDataType = sourceDataType;
        this.isAutoincrement = isAutoincrement;
        this.isGeneratedcolumn = isGeneratedcolumn;
    }
}