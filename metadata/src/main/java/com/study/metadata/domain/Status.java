/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Status
 *
 * @author boyan
 * @version : Status.java, v 0.1 2021-08-12 01:10 boyan
 */
@Data
@ToString
@Accessors(chain = true)
public class Status implements Serializable {
    private static final long serialVersionUID = 2097367899024362069L;

    private Integer weight;
    private Integer height;

}