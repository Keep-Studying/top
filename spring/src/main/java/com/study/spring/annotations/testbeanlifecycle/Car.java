/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.annotations.testbeanlifecycle;

/**
 * @author study
 * @version : Car.java, v 0.1 2020年08月12日 8:27 study Exp $
 */
public class Car {
    public Car() {
        System.out.println("Car的构造方法..........");
    }


    public void init() {
        System.out.println("Car的初始化方法......init");
    }

    public void destroy() {
        System.out.println("Car的销毁方法.....destroy");
    }
}