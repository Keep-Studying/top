/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author study
 * @version : MainClass.java, v 0.1 2020年08月12日 23:57 study Exp $
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        MyService myService = ctx.getBean(MyService.class);
        System.out.println(myService.toString());

        Object myDao2 = ctx.getBean("myDao2");
        System.out.println(myDao2.toString());

        //测试@AutoWired使用的时byName还是byType(默认是使用byType,当发现多个类型的bean话 就通过byName)
        //需要指定装配的名称通过@Qualifier指定名称装配
        BaiDuService baiDuService = ctx.getBean(BaiDuService.class);
        System.out.println(baiDuService);
    }
}