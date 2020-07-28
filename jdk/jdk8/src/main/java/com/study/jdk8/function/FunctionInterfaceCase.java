/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jdk8.function;

/**
 * 函数接口
 * @FunctionalInterface 注解会检测接口是否有且只有一个抽象方法，还可以有默认方法和静态方法
 * @author study
 * @version : FunctionInterfaceCase.java, v 0.1 2020年07月29日 0:55 study Exp $
 */
@FunctionalInterface
public interface FunctionInterfaceCase {

    public void test();

    default String getName(){
        return "人参";
    }

    default String getDesc(){
        return "人参大补元气";
    }

    static String getName2(){
        return "鹿茸";
    }
}