/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * ToString基类
 * @author boyan
 * @version : ToString.java, v 0.1 2021年07月02日 3:24 下午 boyan Exp $
 */
public class ToString implements Serializable {
    private static final long serialVersionUID = 7246485294827021149L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}