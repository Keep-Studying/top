/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.classloader;

import com.study.jvm.memoryModel.OOMTest;

/**
 * 用户对象
 * @author study
 * @version : User.java, v 0.1 2020年06月20日 23:28 study Exp $
 */
public class User {
    private Integer age;
    private String name;


    public void output(){
        System.out.println("Tihs is the output method,"+this.toString());
    }

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("User [");
        builder
                .append("        name=").append(name)
                .append(",        age=").append(age)
                .append(']');
        return builder.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        OOMTest.saveSelfList.add(this);
        System.out.println("关闭资源，User age=" + age + "即将被回收");
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}