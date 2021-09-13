/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * MysqlTable
 *
 * @author boyan
 * @version : MysqlTable.java, v 0.1 2021-08-12 17:52 boyan
 */
@Data
@AllArgsConstructor

@Accessors(chain = true)
public class MysqlTable extends BaseMeta{
    private static final long serialVersionUID = 3684646608845423700L;
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;

    private String engine;
    private Integer version;
    private String rowFormat;
    private Integer tableRows;
    private Integer avgRowLength;
    private Integer dataLength;
    private Integer maxDataLength;
    private Integer indexLength;
    private Integer dataFree;
    private Integer autoIncrement;
    private Date createTime;
    private Date updateTime;
    private Date checkTime;
    private String tableCollation;
    private Integer checksum;
    private String createOptions;
    private String remarks;

    public MysqlTable() {
        super.setConnectorTypeEnum(ConnectorTypeEnum.MYSQL);
    }
}