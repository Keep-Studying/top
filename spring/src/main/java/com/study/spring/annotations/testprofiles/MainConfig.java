/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testprofiles;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * @author study
 * @version : MainConfig.java, v 0.1 2020年08月11日 22:45 study Exp $
 */
@Configuration
@PropertySource(value = {"classpath:ds.properties"})
public class MainConfig implements EmbeddedValueResolverAware {

    @Value("${ds.username}")
    private String userName;

    @Value("${ds.password}")
    private String password;

    private String jdbcUrl;

    private String classDriver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.jdbcUrl = stringValueResolver.resolveStringValue("${ds.jdbcUrl}");
        this.classDriver = stringValueResolver.resolveStringValue("${ds.classDriver}");
    }

    @Bean
    @Profile(value = "test")
    public DataSource testDs() {
        return buildDataSource(new DruidDataSource());
    }

    @Bean
    @Profile(value = "dev")
    public DataSource devDs() {
        return buildDataSource(new DruidDataSource());
    }

    @Bean
    @Profile(value = "prod")
    public DataSource prodDs() {
        return buildDataSource(new DruidDataSource());
    }

    private DataSource buildDataSource(DruidDataSource dataSource) {
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(classDriver);
        dataSource.setUrl(jdbcUrl);
        return dataSource;
    }
}