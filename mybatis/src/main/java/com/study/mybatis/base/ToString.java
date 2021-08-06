/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.mybatis.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 *
 * @author boyan
 * @version : ToString.java, v 0.1 2021年07月18日 11:20 上午 boyan Exp $
 */
public class ToString implements Serializable {
    private static final long serialVersionUID = 6116180711819479155L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}