/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.inject;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @Inject （JSR330规范），需要导入jar包依赖，功能和支持@Primary功能，但是没有Require=false的功能
 * @author study
 * @version : MyService.java, v 0.1 2020年08月12日 23:20 study Exp $
 */
@Service
public class MyService {

    @Inject
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