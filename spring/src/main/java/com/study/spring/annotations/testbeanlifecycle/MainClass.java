/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testbeanlifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月12日 8:35 study Exp $
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        //Car car = ctx.getBean(Car.class);
        ctx.close();
    }
}