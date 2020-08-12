/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.resource;

import org.springframework.stereotype.Repository;

/**
 * @author study
 * @version : MyDao.java, v 0.1 2020年08月12日 23:19 study Exp $
 */
@Repository
public class MyDao {

    private int flag=1;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("MyDao [");
        builder
                .append("        flag=").append(flag)
                .append(']');
        return builder.toString();
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