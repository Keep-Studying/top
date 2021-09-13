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
 * GaussColumn
 * 属性需要再重新核对一遍
 * @author boyan
 * @version : GaussColumn.java, v 0.1 2021-08-14 22:36 boyan
 */
@Data
@ToString
@Accessors(chain = true)
public class GaussColumn implements Serializable {
    private static final long serialVersionUID = 799949956576277776L;

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private Integer ordinalPosition;
    private String columnType;
    private String remarks;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    /**宽度*/
    private Integer characterMaximumLength;
    private Integer characterOctetLength;
    private Integer numericPrecision;
    private Integer numericPrecisionRadix;
    private Integer numericScale;
    private Integer datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String domainCatalog;
    private String domainSchema;
    private String domainName;
    private String udtCatalog;
    private String udtSchema;
    private String maximumCardinality;
    private String dtdIdentifier;
    private String isSelfReferencing;
    private String isIdentity;
    private String identityGeneration;
    private String identityStart;
    private String identityIncrement;
    private String identityMaximum;
    private String identityMinimum;
    private String identityCycle;
    private String isGenerated;
    private String generationExpression;
    private String isUpdatable;
}