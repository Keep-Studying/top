/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testinit;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author study
 * @version : Person.java, v 0.1 2020年08月11日 23:03 study Exp $
 */
public class Person implements InitializingBean {

    public Person() {
        System.out.println("我是构造方法");
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("我是初始化方法");
    }

    @PreDestroy
    public void destory() {
        System.out.println("我是销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}