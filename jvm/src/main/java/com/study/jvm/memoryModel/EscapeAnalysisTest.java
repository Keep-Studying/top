/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.memoryModel;

import com.study.jvm.classloader.User;

/**
 * 对象逃逸分析
 * @author study
 * @version : EscapeAnalysisTest.java, v 0.1 2020年06月25日 10:33 study Exp $
 */
public class EscapeAnalysisTest {

    /**
     * 有返回值，则该对象的作用域范围不确定
     * */
    public User test01(){
        User user = new User();
        user.setAge(1);
        user.setName("zhangsan");
        //todo 保存到数据库
        return user;
    }

    /**
     * 无返回值，则该对象的作用域范围为该方法内部
     * */
    public void test02(){
        User user = new User();
        user.setAge(1);
        user.setName("zhangsan");
        //todo 保存到数据库
    }
}