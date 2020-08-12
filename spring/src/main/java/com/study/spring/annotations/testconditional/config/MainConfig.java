/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testconditional.config;

import com.study.spring.annotations.testconditional.component.Aspect;
import com.study.spring.annotations.testconditional.component.Log;
import com.study.spring.annotations.testconditional.condition.MyCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月11日 23:53 study Exp $
 */
public class MainConfig {
    @Bean
    public Aspect aspect() {
        return new Aspect();
    }

    @Bean
    @Conditional(value = MyCondition.class)
    public Log log() {
        return new Log();
    }
}