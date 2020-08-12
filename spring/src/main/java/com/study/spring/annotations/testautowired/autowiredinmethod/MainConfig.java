/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.autowiredinmethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月12日 23:42 study Exp $
 */
@Configuration
@ComponentScan(basePackages = "com.study.spring.annotations.testautowired.autowiredinmethod")
public class MainConfig {

    @Bean
    public MyAspect myAspect(@Autowired Log log){
       return new MyAspect(log);
    }
}