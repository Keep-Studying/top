/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author boyan
 * @version : BaseRequestConfig.java, v 0.1 2021年07月11日 1:08 上午 boyan Exp $
 */
@Data
public class BaseRequestConfig implements Serializable {
    private static final long serialVersionUID = 2601495065205339481L;

    private Boolean isSsl;
    private String server;
    private Integer port;
    private String userName;
    private String password;
}