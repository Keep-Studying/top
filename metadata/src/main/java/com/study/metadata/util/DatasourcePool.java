/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.study.metadata.domain.DatasourceInfo;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DatasourcePool
 *
 * @author boyan
 * @version : DatasourcePool.java, v 0.1 2021-08-12 17:53 boyan
 */
public class DatasourcePool {

    private static ConcurrentHashMap<String,DruidDataSource> dataSourceMap = new ConcurrentHashMap();
    private static final String COLON = ":";

    public static DataSource getHikariDataSource(DatasourceInfo info){
        String key = info.getUrl()+COLON+info.getUserName();
        DruidDataSource dataSource = dataSourceMap.get(key);
        if(dataSource != null){
            return dataSource;
        }
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName(info.getDriver());
        dataSource.setUrl(info.getUrl());
        dataSource.setUsername(info.getUserName());
        dataSource.setPassword(info.getPassword());
        dataSourceMap.put(key,dataSource);
        return dataSource;
    }
}