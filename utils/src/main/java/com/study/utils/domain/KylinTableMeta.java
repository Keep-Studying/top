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
 * @version : KylinMeta.java, v 0.1 2021年07月08日 3:08 下午 boyan Exp $
 */
@Data
public class KylinTableMeta implements Serializable {
    private static final long serialVersionUID = 7846662222264950590L;

    protected String                table_CAT;
    protected String                table_SCHEM;
    protected String                table_NAME;
    protected String                table_TYPE;
    protected String                remarks;
    protected String                type_CAT;
    protected String                type_SCHEM;
    protected String                type_NAME;
    protected String                self_REFERENCING_COL_NAME;
    protected String                ref_GENERATION;
    private   List<KylinColumnMeta> columns;
    private   List<String>          type;
}