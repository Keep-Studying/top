/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.model;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author study
 * @version : ToString.java, v 0.1 2020年09月24日 0:07 study Exp $
 */
public class ToString implements Serializable {
    private static final long serialVersionUID = -7957696280670621351L;

    @Override
    public String toString() {
        return new StringJoiner(", ", ToString.class.getSimpleName() + "[", "]")
                .toString();
    }
}