/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testfactorybean;

import org.springframework.beans.factory.FactoryBean;

import java.sql.DriverManager;

/**
 * @author study
 * @version : DriverFactoryBean.java, v 0.1 2020年08月11日 23:22 study Exp $
 */
public class DriverFactoryBean implements FactoryBean {

    private String jdbcUrl;

    /**
     * Getter method for property <tt>jdbcUrl</tt>.
     *
     * @return property value of jdbcUrl
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**
     * Setter method for property <tt>jdbcUrl</tt>.
     *
     * @param jdbcUrl value to be assigned to property jdbcUrl
     */
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    @Override
    public Object getObject() throws Exception {
        return DriverManager.getDriver(jdbcUrl);
    }

    @Override
    public Class<?> getObjectType() {
        return java.sql.Driver.class;
    }
}