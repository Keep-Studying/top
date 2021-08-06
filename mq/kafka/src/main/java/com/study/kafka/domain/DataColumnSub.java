/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

/**
 * 数据字段
 * @author boyan
 * @version : DataColumnSub.java, v 0.1 2021年07月02日 6:00 下午 boyan Exp $
 */
@Data
public class DataColumnSub extends ToString {
    private static final long serialVersionUID = 636934985489030183L;

    /**列id：V_ACAM_C01_SANA_PFM_CUST_BRN_MON_SR.LBL_BAL_YEAR_DAY_AVG_MINUS_LAST_MON_SUM(V_ACAM_C01_SANA_PFM_CUST_BRN_MON_SR)*/
    private String columnId;
    /**列的索引：1*/
    private Integer columnIndex;
    /**列名：V_ACAM_C01_SANA_PFM_CUST_BRN_MON_SR.LBL_BAL_YEAR_DAY_AVG_MINUS_LAST_MON_SUM*/
    private String columnName;
    /**列类型：decimal(19,4)/varchar(4096)*/
    private String columnType;
    /**
     * 类型
     * 数据集层面：Date/DateTime/Int/String/Real（除了整数之外的所有数字，双精度）
     * Real
     * */
    private String type;
    /**精度：*/
    private String precision;
    /**备注：负债余额年日均比上月*/
    private String comment;
}