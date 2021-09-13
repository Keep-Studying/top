/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.domain;

import lombok.Data;

/**
 * Result
 *
 * @author boyan
 * @version : Result.java, v 0.1 2021-08-18 14:09 boyan
 */
@Data
public class Result<T> {
    /**
     * 成功状态
     */
    private boolean      success = false;
    /**
     * 处理结果码
     */
    private int          code;
    /**
     * 结果描述
     */
    private String       message;
    /**
     * 结果参数
     */
    private T            data;
}