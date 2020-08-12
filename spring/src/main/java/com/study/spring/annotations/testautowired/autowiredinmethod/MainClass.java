/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.autowiredinmethod;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月12日 23:44 study Exp $
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        MyAspect myAspect =ctx.getBean(MyAspect.class);
        System.out.println(myAspect.toString());

        Object log = ctx.getBean(Log.class);
        System.out.println(log.toString());
    }
}