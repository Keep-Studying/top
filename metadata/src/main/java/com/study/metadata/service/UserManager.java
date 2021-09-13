/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.metadata.service;

import com.mongodb.client.result.DeleteResult;
import com.study.metadata.domain.User;

import java.util.List;

/**
 * UserManager
 *
 * @author boyan
 * @version : UserManager.java, v 0.1 2021-08-11 20:58 boyan
 */
public interface UserManager {

    /**
     * 创建对象
     * @param user
     */
    public User saveUser(User user) ;

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName) ;

    /**
     * 查询对象集合
     * @return
     */
    public List<User> findAll() ;

    /**
     * 更新对象
     * @param user
     */

    public long updateUser(User user) ;

    /**
     * 删除对象
     * @param id
     */

    public DeleteResult deleteUserById(String id) ;
}