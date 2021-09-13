/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * QueryCollectionService
 *
 * @author boyan
 * @version : QueryCollectionService.java, v 0.1 2021-08-12 01:17 boyan
 */
@Service
public class QueryCollectionService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 获取【集合名称】列表
     *
     * @return 集合名称列表
     */
    public Object getCollectionNames() {
        // 执行获取集合名称列表
        return mongoTemplate.getCollectionNames();
    }

    /**
     * 检测集合【是否存在】
     *
     * @return 集合是否存在
     */
    public boolean collectionExists() {
        // 设置集合名称
        String collectionName = "user";
        // 检测新的集合是否存在，返回检测结果
        return mongoTemplate.collectionExists(collectionName);
    }
}