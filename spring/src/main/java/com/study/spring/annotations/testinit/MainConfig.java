/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testinit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean的初始化方法和销毁方法
 * bean的创建——>初始化——>销毁方法
 *
 * 通过JSR250规范提供的注解@PostConstruct和@ProDestory标注的方法
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月11日 23:04 study Exp $
 */
@Configuration
public class MainConfig {

    @Bean
    public Person person() {
        return new Person();
    }
}