/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.service;

import com.study.metadata.domain.Status;
import com.study.metadata.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * InsertService
 *
 * @author boyan
 * @version : InsertService.java, v 0.1 2021-08-12 01:18 boyan
 */
@Service
@Slf4j
public class InsertService {

    /** 设置集合名称 */
    private static final String COLLECTION_NAME = "user";

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 插入【一条】文档数据，如果文档信息已经【存在就抛出异常】
     *
     * @return 插入的文档信息
     */
    public Object insert() {
        // 设置用户信息
        User user = new User()
            .setId("10")
            .setAge(22)
            .setSex("男")
            .setRemake("无")
            .setSalary(1500)
            .setName("zhangsan")
            .setBirthday(new Date())
            .setStatus(new Status().setHeight(180).setWeight(150));
        // 插入一条用户数据，如果文档信息已经存在就抛出异常
        User newUser = mongoTemplate.insert(user, COLLECTION_NAME);
        // 输出存储结果
        log.info("存储的用户信息为：{}", newUser);
        return newUser;
    }

    /**
     * 插入【多条】文档数据，如果文档信息已经【存在就抛出异常】
     *
     * @return 插入的多个文档信息
     *
     */
    public Object insertMany(){
        // 设置两个用户信息
        User user1 = new User()
            .setId("11")
            .setAge(22)
            .setSex("男")
            .setRemake("无")
            .setSalary(1500)
            .setName("shiyi")
            .setBirthday(new Date())
            .setStatus(new Status().setHeight(180).setWeight(150));
        User user2 = new User()
            .setId("12")
            .setAge(22)
            .setSex("男")
            .setRemake("无")
            .setSalary(1500)
            .setName("shier")
            .setBirthday(new Date())
            .setStatus(new Status().setHeight(180).setWeight(150));
        // 使用户信息加入结合
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        // 插入一条用户数据，如果某个文档信息已经存在就抛出异常
        Collection<User> newUserList = mongoTemplate.insert(userList, COLLECTION_NAME);
        // 输出存储结果
        for (User user : newUserList) {
            log.info("存储的用户信息为：{}", user);
        }
        return newUserList;
    }
}