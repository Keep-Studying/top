/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author study
 * @version : BaiDuService.java, v 0.1 2020年08月12日 23:52 study Exp $
 */
@Service
public class BaiDuService {

    @Autowired
    private MyDao myDao2;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("BaiDuService [");
        builder
                .append("        myDao2=").append(myDao2)
                .append(']');
        return builder.toString();
    }

    /**
     * Getter method for property <tt>myDao</tt>.
     *
     * @return property value of myDao
     */
    public MyDao getMyDao() {
        return myDao2;
    }

    /**
     * Setter method for property <tt>myDao</tt>.
     *
     * @param myDao value to be assigned to property myDao
     */
    public void setMyDao(MyDao myDao) {
        this.myDao2 = myDao;
    }
}