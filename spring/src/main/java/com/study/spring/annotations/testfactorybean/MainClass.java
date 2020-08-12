/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testfactorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月11日 23:36 study Exp $
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Object bean = ctx.getBean("carFactoryBean");
        System.out.println(bean);
        Object bean2 = ctx.getBean("&carFactoryBean");
        System.out.println(bean2);

        CarFactoryBean carFactoryBean = ctx.getBean(CarFactoryBean.class);
        System.out.println(bean.getClass());
        System.out.println(bean2.getClass());
        System.out.println(bean2==carFactoryBean);

        System.out.println(ctx.getBean("driverFactoryBean"));
    }
}