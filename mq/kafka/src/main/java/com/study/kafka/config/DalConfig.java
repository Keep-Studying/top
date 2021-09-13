/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 * DalConfig
 *
 * @author boyan
 * @version : DalConfig.java, v 0.1 2021-09-01 00:13 boyan
 */
@EnableTransactionManagement
@Configuration
@MapperScan(value = {"com.study.kafka.daointerface"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class DalConfig {

    @Bean
    public DefaultSqlInjector sqlInjector() {
        return new DefaultSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
                List<AbstractMethod> abstractMethods = super.getMethodList(mapperClass);
                //批量插入
                abstractMethods.add(new InsertBatchSomeColumn());
                return abstractMethods;
            }
        };
    }

    /**
     * 最新版分页插件
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}