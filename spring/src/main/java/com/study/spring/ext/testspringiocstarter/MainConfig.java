/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.ext.testspringiocstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wstudy
 * @version : MainConfig.java, v 0.1 2020年08月13日 11:12 wstudy Exp $
 */
@Configuration
@Import(value = {MyService.class})
@ComponentScan(basePackages = "com.study.spring.ext.testspringiocstarter")
public class MainConfig {

    @Bean
    public DataSource dataSource(){
        return new DataSource();
    }
}