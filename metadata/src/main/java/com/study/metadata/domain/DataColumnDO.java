/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DataColumnDO
 *
 * @author boyan
 * @version : DataColumnDO.java, v 0.1 2021-08-19 20:03 boyan
 */
@Data
public class DataColumnDO implements Serializable {

    private static final long    serialVersionUID = -7313244946609421595L;
    /**
     * 主键
     */
    private              Long    id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 创建者
     */
    private              String  createOperator;
    /**
     * 修改时间
     */
    private              Date    gmtModified;
    /**
     * 修改者
     */
    private              String  modifiedOperator;
    /**
     * 列ID
     */
    private              String  columnId;
    /**
     * 表模式
     */
    private              String  tableSchema;
    /**
     * 表目录
     */
    private              String  tableCat;
    /**
     * 表名
     */
    private              String  tableName;
    /**
     * 列名
     */
    private              String  columnName;
    /**
     * 数据类型(java.sql.Types的SQL类型)
     */
    private              Integer dataType;
    /**
     * 数据源依赖的类型名称
     */
    private              String  typeName;
    /**
     * 数据集依赖的类型名称
     */
    private              String  type;
    /**
     * 列的大小
     */
    private              Integer columnSize;
    /**
     * 缓冲长度(未使用)
     */
    private              Integer bufferLength;
    /**
     * 小数部分的位数
     */
    private              Integer decimalDigits;
    /**
     * 基数(通常为10或2)
     */
    private              Integer numPrecRadix;
    /**
     * 是否允许使用NULL
     */
    private              Integer nullable;
    /**
     * 备注
     */
    private              String  remarks;
    /**
     * 该列的默认值
     */
    private              String  columnDef;
    /**
     * sql的数据类型(未使用)
     */
    private              Integer sqlDataType;
    /**
     * sql的日期(未使用)
     */
    private              Integer sqlDatetimeSub;
    /**
     * char类型时列中的最大字节数
     */
    private              Integer charOctetLength;
    /**
     * 表中的列的索引(从1开始)
     */
    private              Integer ordinalPosition;
    /**
     * ISO规则确定列是否包括null
     */
    private              String  isNullable;
    /**
     * 表类别的作用域
     */
    private              String  scopeCatlog;
    /**
     * 表模式的作用域
     */
    private              String  scopeSchema;
    /**
     * 表的作用域
     */
    private              String  scopeTable;
    /**
     * 不同类型或用户生成Ref类型
     */
    private              Integer sourceDataType;
    /**
     * 此列是否自动增加
     */
    private              String  isAutoincrement;
    /**
     * 数据源ID
     */
    private              String  datasourceId;
    /**
     * 数据集ID
     */
    private              Long    datasetId;
    /**
     * 数据表ID
     */
    private              Long    datatableId;
    /**
     * 权限
     */
    private String privileges;
    /**
     * 额外
     */
    private String extra;
    /**
     * 字符集
     */
    private String characterSetName;
    /**
     * 排序规则
     */
    private String collationName;
    /**
     * 字符最大长度
     */
    private Integer characterMaximumLength;
    /**
     * 指示此列是否自动增加：""
     */

    private Integer numericScale;
}