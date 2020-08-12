/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testbeanlifecycle;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author study
 * @version : Log.java, v 0.1 2020年08月12日 8:31 study Exp $
 */
public class Log implements InitializingBean {

    public Log() {
        System.out.println("我是Log的构造方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是Log的 afterPropertiesSet方法");
    }


    public void init() {
        System.out.println("我是log的init方法");
    }
}