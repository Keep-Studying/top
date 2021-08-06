/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.domain;

import lombok.Data;

/**
 *
 * @author boyan
 * @version : HandlerContext.java, v 0.1 2021年07月16日 2:51 下午 boyan Exp $
 */
@Data
public class HandlerContext {


    private String className;
    private String method;
    private Object[] params;

    public HandlerContext() {
    }

    public HandlerContext(String className, String method) {
        this.className = className;
        this.method = method;
    }

    public HandlerContext(String className, String method, Object... params) {
        this.className = className;
        this.method = method;
        this.params = params;
    }
}