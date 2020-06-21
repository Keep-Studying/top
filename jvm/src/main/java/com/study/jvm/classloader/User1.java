/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.classloader;

/**
 * 用户对象
 * @author study
 * @version : User.java, v 0.1 2020年06月20日 23:28 study Exp $
 */
public class User1 {
    private String name;

    private Integer age;

    public void output(){
        System.out.println("Tihs is the output method,"+this.toString());
    }

    public User1() {
    }

    public User1(String name, Integer age) {
        this.name = name;
        this.age = age;
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