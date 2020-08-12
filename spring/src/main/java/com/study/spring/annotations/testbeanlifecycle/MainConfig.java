/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testbeanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月12日 8:31 study Exp $
 */
@Configuration
@ComponentScan(basePackages = "com.study.spring.annotations.testbeanlifecycle")
public class MainConfig {
    @Scope(value = "prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){return new Car();}

    @Bean(initMethod = "init")
    public Log log(){return new Log();}

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){return new MyBeanPostProcessor();}
}