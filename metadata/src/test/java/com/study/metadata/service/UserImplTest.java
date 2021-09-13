package com.study.metadata.service;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.metadata.MetadataApplicationTests;
import com.study.metadata.domain.User;

/**
 * UserImplTest
 *
 * @author boyan
 * @version : UserImplTest.java, v 0.1 2021/8/11 20:37 boyan Exp $
 */
public class UserImplTest extends MetadataApplicationTests {

    @Autowired
    private UserManagerImpl usrImpl;

    @org.junit.Test public void saveUser() {
        User user = usrImpl.saveUser(constructUser());
        System.out.println(user);
    }

    @org.junit.Test public void findUserByUserName() {
        User lisi = usrImpl.findUserByUserName("lisi");
        System.out.println(lisi);
    }

    @org.junit.Test public void updateUser() {
        User user = constructUser();
        user.setName("zhaoliu");
        long count = usrImpl.updateUser(user);
        System.out.println(count);
    }

    @org.junit.Test public void deleteUserById() {
        User lisi = usrImpl.findUserByUserName("zhaoliu");
        DeleteResult deleteResult = usrImpl.deleteUserById(lisi.getId());
        System.out.println(deleteResult);
    }

    private User constructUser(){
        User user = new User();
        user.setAge(19);
        user.setName("lisi");
        user.setSex("m");
        return user;
    }
}