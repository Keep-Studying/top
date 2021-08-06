/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author boyan
 * @version : MainConfig.java, v 0.1 2021年07月15日 12:12 上午 boyan Exp $
 */
@ComponentScan("com.study.spring.aop")
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
public class MainConfig {

    @Bean
    public Calculate calculate(){
        return new CalculateImpl();
    }

    //@Bean
    //public DalLogAspect dalLogAspect(){
    //    return new DalLogAspect();
    //}
}