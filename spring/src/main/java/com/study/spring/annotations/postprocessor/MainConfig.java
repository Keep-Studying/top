/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.postprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月13日 0:15 study Exp $
 */
@Configuration
@ComponentScan(basePackages = "com.study.spring.annotations.postprocessor")
public class MainConfig {

    @Bean(initMethod = "init")
    public Compent compent() {
        return new Compent();
    }
}