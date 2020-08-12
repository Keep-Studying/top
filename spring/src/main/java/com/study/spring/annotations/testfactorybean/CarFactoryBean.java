/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testfactorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 通过实现factoryBean接口往容器中注册组件
 * @author study
 * @version : CarFactoryBean.java, v 0.1 2020年08月11日 23:21 study Exp $
 */
public class CarFactoryBean implements FactoryBean<Car> {
    @Override
    public Car getObject() throws Exception {
        return new Car();
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}