/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.ext.testbfpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月13日 11:32 study Exp $
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println(ctx.getBean(Log.class));

    }
}