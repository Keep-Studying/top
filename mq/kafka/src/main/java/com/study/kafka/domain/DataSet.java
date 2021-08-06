/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

import java.util.List;

/**
 * 数据集
 * @author boyan
 * @version : DataSet.java, v 0.1 2021年07月02日 5:57 下午 boyan Exp $
 */
@Data
public class DataSet extends ToString {
    private static final long             serialVersionUID = -300677899461480597L;
    /**ID*/
    private Long             id;
    /**dataSetId+version*/
    private String           uk;
    /**数据集ID:管会数据月表分析数据源(移动).905d0576-113a-47e6-966b-337f6832418f*/
    private String           dataSetId;
    /**数据集名称:管会数据月表分析数据源(移动)，
     * 在分析侧，不带版本号（覆盖）
     * 在报告侧，会带有版本号（新建）
     */
    private String           name;
    /**版本:0.37*/
    private String           version;
    /**门户：火眼 / 智策*/
    private String           portal;
    /**连接集合*/
    private List<Connection> connections;
    /**认证列表：即权限列表： */
    private List<DataAuth>                                                    dataAuths;
    /**数据集字段集合*/
    private List<DataColumnSub> columns;
    /**表*/
    private List<DataTableSub>                                                tables;

}

