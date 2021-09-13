/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DataTableDO
 *
 * @author boyan
 * @version : DataTableDO.java, v 0.1 2021-08-19 20:02 boyan
 */
@Data
public class DataTableDO implements Serializable {

    /**
     * 连接类型
     */
    protected String connectorType;
    /**
     * 主键
     */
    private              Long               id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 创建者
     */
    private              String             createOperator;
    /**
     * 修改时间
     */
    private              Date               gmtModified;
    /**
     * 修改者
     */
    private              String             modifiedOperator;
    /**
     * 表id
     */
    private              String             tableId;
    /**
     * 表模式
     */
    private              String             tableSchema;
    /**
     * 表名
     */
    private              String             tableName;
    /**
     * 表目录
     */
    private              String             tableCat;
    /**
     * 表类型
     */
    private              String             tableType;
    /**
     * 备注
     */
    private              String             remarks;
    /**
     * 目录类型
     */
    private              String             typeCat;
    /**
     * 模式类型
     */
    private              String             typeSchema;
    /**
     * 表名类型
     */
    private              String             typeName;
    /**
     * 数据源ID
     */
    private              String             datasourceId;
    /**
     * 数据集id
     */
    private              Long               datasetId;
    /**
     * 表的行数
     */
    private Long tableRows;
    /**
     * 数据长度
     */
    private Long dataLength;
    /**
     * 索引长度
     */
    private Long indexLength;
    /**
     * 自增值
     */
    private Long autoIncrement;
    /**
     * 引擎
     */
    private String engine;
    /**
     * 表的字符序
     */
    private String tableCollation;
    /**
     * 列集合
     */
    private List<DataColumnDO> columns = new ArrayList<>();
    public void addColumn(DataColumnDO column) {
        this.columns.add(column);
    }
}