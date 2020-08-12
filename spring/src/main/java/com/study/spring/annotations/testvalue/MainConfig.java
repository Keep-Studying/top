/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testvalue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月11日 22:34 study Exp $
 */
@Configuration
@PropertySource(value = {"classpath:person.properties"})
public class MainConfig {
    @Bean
    public Person person(){
        return new Person();
    }
}