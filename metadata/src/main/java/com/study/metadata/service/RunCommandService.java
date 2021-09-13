/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.service;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RunCommandService
 *
 * @author boyan
 * @version : RunCommandService.java, v 0.1 2021-08-12 01:28 boyan
 */
@Service
public class RunCommandService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 执行 mongoDB 自定义命令，详情可以查看：https://docs.mongodb.com/manual/reference/command/
     *
     * @return 执行命令返回结果的 Json 结果
     * @description 执行自定义 mongoDB 命令
     */
    public Object runCommand() {
        // 自定义命令
        String jsonCommand = "{\"buildInfo\":1}";
        // 将 JSON 字符串解析成 MongoDB 命令
        Bson bson = Document.parse(jsonCommand);
        // 执行自定义命令
        return mongoTemplate.getDb().runCommand(bson);
    }
}