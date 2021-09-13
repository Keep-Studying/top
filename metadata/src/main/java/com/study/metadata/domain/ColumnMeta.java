/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ColumnMeta
 *
 * @author boyan
 * @version : ColumnMeta.java, v 0.1 2021-08-16 23:40 boyan
 */
@Data
@Accessors(chain = true)
public class ColumnMeta implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String TABLE_CAT;
    protected String TABLE_SCHEM;
    protected String TABLE_NAME;
    protected String COLUMN_NAME;
    protected int DATA_TYPE;
    protected String TYPE_NAME;
    protected int COLUMN_SIZE;
    protected int BUFFER_LENGTH;
    protected int DECIMAL_DIGITS;
    protected int NUM_PREC_RADIX;
    protected int NULLABLE;
    protected String REMARKS;
    protected String COLUMN_DEF;
    protected int SQL_DATA_TYPE;
    protected int SQL_DATETIME_SUB;
    protected int CHAR_OCTET_LENGTH;
    protected int ORDINAL_POSITION;
    protected String IS_NULLABLE;
    protected String SCOPE_CATLOG;
    protected String SCOPE_SCHEMA;
    protected String SCOPE_TABLE;
    protected short SOURCE_DATA_TYPE;
    protected String IS_AUTOINCREMENT;

    public ColumnMeta() {
    }

    public ColumnMeta(String tABLE_CAT, String tABLE_SCHEM, String tABLE_NAME, String cOLUMN_NAME, int dATA_TYPE, String tYPE_NAME, int cOLUMN_SIZE, int bUFFER_LENGTH, int dECIMAL_DIGITS, int nUM_PREC_RADIX, int nULLABLE, String rEMARKS, String cOLUMN_DEF, int sQL_DATA_TYPE, int sQL_DATETIME_SUB, int cHAR_OCTET_LENGTH, int oRDINAL_POSITION, String iS_NULLABLE, String sCOPE_CATLOG, String sCOPE_SCHEMA, String sCOPE_TABLE, short sOURCE_DATA_TYPE, String iS_AUTOINCREMENT) {
        super();
        TABLE_CAT = tABLE_CAT;
        TABLE_SCHEM = tABLE_SCHEM;
        TABLE_NAME = tABLE_NAME;
        COLUMN_NAME = cOLUMN_NAME;
        DATA_TYPE = dATA_TYPE;
        TYPE_NAME = tYPE_NAME;
        COLUMN_SIZE = cOLUMN_SIZE;
        BUFFER_LENGTH = bUFFER_LENGTH;
        DECIMAL_DIGITS = dECIMAL_DIGITS;
        NUM_PREC_RADIX = nUM_PREC_RADIX;
        NULLABLE = nULLABLE;
        REMARKS = rEMARKS;
        COLUMN_DEF = cOLUMN_DEF;
        SQL_DATA_TYPE = sQL_DATA_TYPE;
        SQL_DATETIME_SUB = sQL_DATETIME_SUB;
        CHAR_OCTET_LENGTH = cHAR_OCTET_LENGTH;
        ORDINAL_POSITION = oRDINAL_POSITION;
        IS_NULLABLE = iS_NULLABLE;
        SCOPE_CATLOG = sCOPE_CATLOG;
        SCOPE_SCHEMA = sCOPE_SCHEMA;
        SCOPE_TABLE = sCOPE_TABLE;
        SOURCE_DATA_TYPE = sOURCE_DATA_TYPE;
        IS_AUTOINCREMENT = iS_AUTOINCREMENT;
    }
}