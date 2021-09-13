/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 数据表
 * @author boyan
 * @version : DataTableSub.java, v 0.1 2021年07月02日 6:01 下午 boyan Exp $
 */
@Data
@Accessors(chain = true)
public class DataTableSub extends ToString {
    private static final long serialVersionUID = 4221950844933772579L;

    /**ID：V_ACAM_C01_SANA_PFM_CUST_BRN_MON_SR*/
    private Long             id;

    /**ID：V_ACAM_C01_SANA_PFM_CUST_BRN_MON_SR*/
    private String             dataTableId;
    /**
     * 表名
     * P_ACAM_VC.V_ACAM_C01_SANA_PFM_CUST_BRN_MON_SR
     * tableName
     * */
    private String              name;
    /**数据源id：*/
    private String              dataSourceId;
    /**表模式：DEFAULT/dbName*/
    private String              schemaName;
    /**表类别*/
    private String              catalogName;
    /**字段集合*/
    private List<DataColumnSub> columns;
}