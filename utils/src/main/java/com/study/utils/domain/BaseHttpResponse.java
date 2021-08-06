/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.domain;

import lombok.Data;

/**
 *
 * @author boyan
 * @version : BaseHttpResponse.java, v 0.1 2021年07月11日 9:42 上午 boyan Exp $
 */
@Data
public class BaseHttpResponse<T> {

    private String code;

    private String msg;

    private T data;
}