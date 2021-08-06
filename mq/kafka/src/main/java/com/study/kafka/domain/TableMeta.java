/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

import java.util.List;

/**
 * 表元数据
 * @author boyan
 * @version : TableMeta.java, v 0.1 2021年07月02日 5:52 下午 boyan Exp $
 */
@Data
public class TableMeta extends ToString {
    private static final long             serialVersionUID = -8415066171452488595L;
    /**ID*/
    private Long   id;
    /**表模式:KAP*/
    private String TABLE_SCHEM;
    /**表名:TEST*/
    private String TABLE_NAME;
    /**表目录:defaultCatalog*/
    private String TABLE_CAT;
    /**表类型：TABLE*/
    private String TABLE_TYPE;
    /**备注*/
    private String REMARKS;
    /**目录类型*/
    private String TYPE_CAT;
    /**模式类型*/
    private String TYPE_SCHEM;
    /**类型名*/
    private String TYPE_NAME;
    private List<ColumnMeta> columns;
}