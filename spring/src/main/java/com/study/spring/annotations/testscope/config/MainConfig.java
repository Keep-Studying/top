/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testscope.config;

import com.study.spring.annotations.testscope.compent.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 配置Bean的作用域对象:
 * 1.在不指定@Scope的情况下，所有的bean都是单实例的bean，而且是饿汉式加载（容器启动实例就创建好了）
 * 2.指定@Scope为prototype，便是为多实例的，而且是懒汉式加载（IOC容器启动的时候，并不会创建对象，而是第一次使用的时候才会创建）
 * 3.@Scope指定的作用域方法取值:
 *   singleton，单实例的（默认）
 *   prototype，多实例的
 *   request，同一次请求的
 *   session，同一个会话级别的
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月11日 22:42 study Exp $
 */
@Configuration
public class MainConfig {
    /**
     * 配置的bean 默认是单实例的
     * @return
     */
    @Bean
    @Lazy
    //@Scope(scopeName = "prototype")
    public Person person() {
        return new Person();
    }
}