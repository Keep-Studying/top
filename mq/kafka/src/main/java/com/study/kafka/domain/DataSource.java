/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

/**
 * 数据源
 * @author boyan
 * @version : DataSource.java, v 0.1 2021年07月02日 5:50 下午 boyan Exp $
 */
@Data
public class DataSource extends ToString {
    private static final long serialVersionUID = -6274699114310525203L;

    /**数据源id：15*/
    private String id;
    /**连接server（IP）：ke.cmbchina.net*/
    private String server;
    /**端口：8080*/
    private String port;
    /**项目名称/dbName：ANA3*/
    private String project;
    /**账户:ANA3_HUOYAN*/
    private String userName;
    /**密码*/
    private String password;
    /**名字*/
    private String name;
    /**数据源类型：KYLIN/MYSQL*/
    private String type;
}