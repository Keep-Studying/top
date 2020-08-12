/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testbeanlifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author study
 * @version : Book.java, v 0.1 2020年08月12日 8:26 study Exp $
 */
public class Book {
    public Book() {
        System.out.println("book 的构造方法");
    }

    @PostConstruct
    public void init() {
        System.out.println("book 的PostConstruct标志的方法");
    }

    @PreDestroy
    public void destory() {
        System.out.println("book 的PreDestory标注的方法");
    }
}