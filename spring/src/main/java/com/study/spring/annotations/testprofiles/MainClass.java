/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testprofiles;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月11日 22:51 study Exp $
 */
public class MainClass {

    public static void main(String[] args) {
        //代码方式设置profile参数
        //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        //ctx.getEnvironment().setActiveProfiles("test","dev");

        //通过JVM运行时参数来设置profile参数,-Dspring.profiles.active=test|dev|prod
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(MainConfig.class);
        ctx.refresh();
        printBeanName(ctx);
    }

    private static void printBeanName(AnnotationConfigApplicationContext ctx){
        for(String beanName:ctx.getBeanDefinitionNames()) {
            System.out.println("容器中的BeanName："+beanName);
        }
    }
}