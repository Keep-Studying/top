/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testautowired.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月12日 23:30 study Exp $
 */
@Configuration
@ComponentScan(basePackages = "com.study.spring.annotations.testautowired.inject")
public class MainConfig {

    @Primary
    @Bean
    public MyDao myDao2() {
        MyDao myDao = new MyDao();
        myDao.setFlag(2);
        return myDao;
    }
}