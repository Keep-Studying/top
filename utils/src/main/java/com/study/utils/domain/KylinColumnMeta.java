/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author boyan
 * @version : KylinColumnMeta.java, v 0.1 2021年07月08日 3:13 下午 boyan Exp $
 */
@Data
public class KylinColumnMeta implements Serializable {
    private static final long serialVersionUID = -3269546111385099277L;

    private   List<String> type;
    protected String       table_CAT;
    protected String       table_SCHEM;
    protected String       table_NAME;
    protected String       column_NAME;
    protected int          data_TYPE;
    protected String       type_NAME;
    protected int          column_SIZE;
    protected int          buffer_LENGTH;
    protected int          decimal_DIGITS;
    protected int          num_PREC_RADIX;
    protected int          nullable;
    protected String       remarks;
    protected String       column_DEF;
    protected int          sql_DATA_TYPE;
    protected int          sql_DATETIME_SUB;
    protected int          char_OCTET_LENGTH;
    protected int          ordinal_POSITION;
    protected String       is_NULLABLE;
    protected String       scope_CATLOG;
    protected String       scope_SCHEMA;
    protected String       scope_TABLE;
    protected Integer      source_DATA_TYPE;
    protected String       is_AUTOINCREMENT;
}