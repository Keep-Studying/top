/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * GaussTable
 *
 * @author boyan
 * @version : GaussTable.java, v 0.1 2021-08-14 22:35 boyan
 */
@Data
@ToString
@Accessors(chain = true)
public class GaussTable extends BaseMeta {
    private static final long serialVersionUID = 2840213186015675429L;

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;

    private String selfReferencingColumnName;
    private String referenceGeneration;
    private String userDefinedTypeCatalog;
    private String userDefinedTypeSchema;
    private String userDefinedTypeName;
    private String isInsertableInto;
    private String isTyped;
    private String commitAction;

    public GaussTable() {
        super.setConnectorTypeEnum(ConnectorTypeEnum.GAUSS);
    }
}