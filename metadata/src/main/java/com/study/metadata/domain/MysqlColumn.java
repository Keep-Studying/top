/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * MysqlColumn
 *
 * @author boyan
 * @version : MysqlColumn.java, v 0.1 2021-08-14 22:40 boyan
 */
@Data
@ToString
@Accessors(chain = true)
public class MysqlColumn implements Serializable {
    private static final long serialVersionUID = 8748816447203584526L;

    private String tableCatalog;
    private String tableSchema;
    private String tableName;

    private String columnName;
    private Integer ordinalPosition;
    private String columnDefault;
    /**YES / NO*/
    private String isNullable;
    private String dataType;
    /**宽度*/
    private Integer characterMaximumLength;
    private Integer characterOctetLength;
    private Integer numericPrecision;
    private Integer numericScale;
    private Integer datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String remarks;
    private String generationExpression;
    private Integer srsId;
}