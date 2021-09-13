/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.study.metadata.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * UserImpl
 *
 * @author boyan
 * @version : UserManagerImpl.java, v 0.1 2021-08-11 20:32 boyan
 */
//@Service
public class UserManagerImpl implements UserManager{

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param user
     */
    @Override
    public User saveUser(User user) {
        User save = mongoTemplate.save(user);
        return save;
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        User user =  mongoTemplate.findOne(query , User.class);
        return user;
    }

    /**
     * 查询对象集合
     * @return
     */
    @Override
    public List<User>  findAll() {

        List<User> all = mongoTemplate.findAll(User.class);
        return all;
    }

    /**
     * 更新对象
     * @param user
     */
    @Override
    public long updateUser(User user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("name", user.getName()).set("age", user.getAge());
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null){
            return result.getMatchedCount();

        } else{
            return 0;
        }
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public DeleteResult deleteUserById(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        DeleteResult remove = mongoTemplate.remove(query, User.class);
        return remove;
    }
}