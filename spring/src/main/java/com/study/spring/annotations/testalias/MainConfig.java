/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testalias;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月13日 0:08 study Exp $
 */
@Configuration
public class MainConfig {

    @Bean(name = {"aliasBean","aliasBean2"})
    public AliasBean aliasBean() {
        return new AliasBean();
    }
}