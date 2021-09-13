/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * BaseMeta
 *
 * @author boyan
 * @version : BaseMeta.java, v 0.1 2021-08-17 20:40 boyan
 */
@Data
public class BaseMeta implements Serializable {
    private static final long serialVersionUID = -6836592460943139976L;

    private ConnectorTypeEnum connectorTypeEnum;
}