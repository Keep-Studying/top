/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.ext.testbfpostprocessor;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author study
 * @version : Log.java, v 0.1 2020年08月13日 11:17 study Exp $
 */
public class Log {

    @Value("1")
    private int flag;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Log{");
        sb.append("flag=").append(flag);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Getter method for property <tt>flag</tt>.
     *
     * @return property value of flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Setter method for property <tt>flag</tt>.
     *
     * @param flag value to be assigned to property flag
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
}