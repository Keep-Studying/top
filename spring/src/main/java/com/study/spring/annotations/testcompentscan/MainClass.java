/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testcompentscan;

import com.study.spring.annotations.testcompentscan.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月12日 0:12 study Exp $
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinationNames = ctx.getBeanDefinitionNames();
        for (String name:beanDefinationNames) {
            System.out.println("bean的定义信息:"+name);
        }
    }
}