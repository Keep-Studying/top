/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.resource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月12日 23:23 study Exp $
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        MyService myService = ctx.getBean(MyService.class);
        System.out.println(myService.toString());

        Object myDao = ctx.getBean("myDao");
        System.out.println(myDao.toString());

        Object myDao2 = ctx.getBean("myDao2");
        System.out.println(myDao2.toString());
    }
}