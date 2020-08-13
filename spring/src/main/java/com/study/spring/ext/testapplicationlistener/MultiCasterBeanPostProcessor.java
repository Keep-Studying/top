/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.ext.testapplicationlistener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * @author study
 * @version : MultiCasterBeanPostProcessor.java, v 0.1 2020年08月13日 11:45 study Exp $
 */
@Component
public class MultiCasterBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof SimpleApplicationEventMulticaster){
            System.out.println("我拦截到了");
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster= (SimpleApplicationEventMulticaster) bean;
            simpleApplicationEventMulticaster.setTaskExecutor(Executors.newSingleThreadExecutor());
            return bean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}