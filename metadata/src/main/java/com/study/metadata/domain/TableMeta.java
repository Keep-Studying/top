/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TableMeta
 *
 * @author boyan
 * @version : TableMeta.java, v 0.1 2021-08-16 23:40 boyan
 */
@Data
@Accessors(chain = true)
public class TableMeta implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 表模式:KAP
     */
    private              String           tableSchem;
    /**
     * 表名:TEST
     */
    private              String           tableName;
    /**
     * 表目录:defaultCatalog
     */
    private              String           tableCat;
    /**
     * 表类型：TABLE
     */
    private              String           tableType;
    /**
     * 备注
     */
    private              String           remarks;
    /**
     * 目录类型
     */
    private              String           typeCat;
    /**
     * 模式类型
     */
    private              String           typeSchem;
    /**
     * 类型名
     */
    private              String           typeName;


    protected String TABLE_CAT;
    protected String TABLE_SCHEM;
    protected String TABLE_NAME;
    protected String TABLE_TYPE;
    protected String REMARKS;
    protected String TYPE_CAT;
    protected String TYPE_SCHEM;
    protected String TYPE_NAME;
    protected String SELF_REFERENCING_COL_NAME;
    protected String REF_GENERATION;
    private List<ColumnMeta> columns = new ArrayList<ColumnMeta>();

    public TableMeta() {
    }

    public TableMeta(String tABLE_CAT, String tABLE_SCHEM, String tABLE_NAME, String tABLE_TYPE, String rEMARKS, String tYPE_CAT, String tYPE_SCHEM, String tYPE_NAME, String sELF_REFERENCING_COL_NAME, String rEF_GENERATION) {
        super();
        TABLE_CAT = tABLE_CAT;
        TABLE_SCHEM = tABLE_SCHEM;
        TABLE_NAME = tABLE_NAME;
        TABLE_TYPE = tABLE_TYPE;
        REMARKS = rEMARKS;
        TYPE_CAT = tYPE_CAT;
        TYPE_SCHEM = tYPE_SCHEM;
        TYPE_NAME = tYPE_NAME;
        SELF_REFERENCING_COL_NAME = sELF_REFERENCING_COL_NAME;
        REF_GENERATION = rEF_GENERATION;
    }


    public void addColumn(ColumnMeta column) {
        this.columns.add(column);
    }
}