/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.resource;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Resource （JSR250规范），功能和@AutoWired的功能差不多，但是不支持@Primary和@Qualifier
 * @author study
 * @version : MyService.java, v 0.1 2020年08月12日 23:20 study Exp $
 */
@Service
public class MyService {

    /**
     * @resource 是不支持@Primary的
     * */
    @Resource
    private MyDao myDao;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("MyService [");
        builder
                .append("        myDao=").append(myDao)
                .append(']');
        return builder.toString();
    }

    /**
     * Getter method for property <tt>myDao</tt>.
     *
     * @return property value of myDao
     */
    public MyDao getMyDao() {
        return myDao;
    }

    /**
     * Setter method for property <tt>myDao</tt>.
     *
     * @param myDao value to be assigned to property myDao
     */
    public void setMyDao(MyDao myDao) {
        this.myDao = myDao;
    }
}