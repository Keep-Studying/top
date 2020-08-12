/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.autowiredinmethod;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author study
 * @version : MyAspect.java, v 0.1 2020年08月12日 23:40 study Exp $
 */
public class MyAspect {

    private Log log;

    /**
     * Getter method for property <tt>log</tt>.
     *
     * @return property value of log
     */
    public Log getLog() {
        return log;
    }

    /**
     * Setter method for property <tt>log</tt>.
     *
     * @param log value to be assigned to property log
     */
    public void setLog(Log log) {
        this.log = log;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("MyAspect [");
        builder
                .append("        log=").append(log)
                .append(']');
        return builder.toString();
    }

    @Autowired
    public MyAspect(Log log) {
        this.log = log;
    }
}