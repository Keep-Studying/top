/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

/**
 * 数据源连接
 * @author boyan
 * @version : Connection.java, v 0.1 2021年07月02日 5:58 下午 boyan Exp $
 */
@Data
public class Connection extends ToString {
    private static final long  serialVersionUID = 7410641893421792926L;

    /**KYLIN时填cube名：ACAM_C01_SANA_PFM_CUST_BRN_MON_SR_202012_KC*/
    private String             tableName;
    /**数据源id：*/
    private String             dataSourceId;
    /**类别：KYLIN/MYSQL*/
    private String             type;
}