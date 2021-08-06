/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import lombok.Data;

/**
 * 列元数据
 * 
 * @author boyan
 * @version : ColumnMeta.java, v 0.1 2021年07月02日 5:51 下午 boyan Exp $
 */
@Data
public class ColumnMeta extends ToString {
    private static final long serialVersionUID = -3873596892472420298L;

    /** 表类别（可为 null）：defaultCatalog */
    private String TABLE_CAT;
    /** 表模式（可为 null）：KAP */
    private String TABLE_SCHEM;
    /** 表名称：TEST */
    private String TABLE_NAME;
    /** 列名称：SSS */
    private String COLUMN_NAME;
    /**
     * 来自 java.sql.Types 的 SQL 类型 public final static int VARCHAR = 12;
     */
    private Integer DATA_TYPE;
    /**
     * 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的 ："VARCHAR(4096) CHARACTER SET \"UTF-16LE\" COLLATE \"UTF-16LE$en_US$primary\""
     */
    private String TYPE_NAME;
    /** 列的大小：4096 */
    private Integer COLUMN_SIZE;
    /** 未使用：-1 */
    private Integer BUFFER_LENGTH;
    /** 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null：0 */
    private Integer DECIMAL_DIGITS;
    /** 基数（通常为 10 或 2）：10 */
    private Integer NUM_PREC_RADIX;
    /** 是否允许使用 NULL：1 */
    private Integer NULLABLE;
    /** 描述列的注释（可为 null）： */
    private String REMARKS;
    /** 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null） */
    private String COLUMN_DEF;
    /** 未使用：-1 */
    private Integer SQL_DATA_TYPE;
    /** 未使用：-1 */
    private Integer SQL_DATETIME_SUB;
    /** 对于 char 类型，该长度是列中的最大字节数：4096 */
    private Integer CHAR_OCTET_LENGTH;
    /** 表中的列的索引（从 1 开始）：1 */
    private Integer ORDINAL_POSITION;
    /**
     * ISO 规则用于确定列是否包括 null YES --- 如果参数可以包括 NULL NO --- 如果参数不可以包括 NULL 空字符串 --- 如果不知道参数是否可以包括 null
     */
    private String IS_NULLABLE;
    /** 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） */
    private String SCOPE_CATLOG;
    /** 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） */
    private String SCOPE_SCHEMA;
    /** 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null） */
    private String SCOPE_TABLE;
    /**
     * 不同类型或用户生成 Ref 类型、 来自 java.sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是 DISTINCT 或用户生成的 REF，则为 null） ：-1
     */
    private Integer SOURCE_DATA_TYPE;
    /** 指示此列是否自动增加："" */
    private String IS_AUTOINCREMENT;
}