/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testscope;

import com.study.spring.annotations.testscope.compent.Person;
import com.study.spring.annotations.testscope.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月11日 22:42 study Exp $
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) ctx.getBean("person");
        Person person2 = (Person) ctx.getBean("person");
        System.out.println(person==person2);
    }
}