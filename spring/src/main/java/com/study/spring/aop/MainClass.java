/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author boyan
 * @version : MainClass.java, v 0.1 2021年07月15日 12:13 上午 boyan Exp $
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

        Calculate calculate = (Calculate) ctx.getBean("calculate");

        //int retVal = calculate.mod(2,4);
        calculate.add(6,2);
    }
}