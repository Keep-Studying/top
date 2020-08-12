/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月12日 23:22 study Exp $
 */
@Configuration
@ComponentScan(basePackages = "com.study.spring.annotations.testautowired.resource")
public class MainConfig {

    @Primary
    @Bean
    public MyDao myDao2() {
        return new MyDao();
    }
}