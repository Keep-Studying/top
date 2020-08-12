/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testconfiuration.config;

import com.study.spring.annotations.testconfiuration.compent.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月11日 23:46 study Exp $
 */
@Configuration
public class MainConfig {

    @Bean
    public Person person(){return new Person();}
}