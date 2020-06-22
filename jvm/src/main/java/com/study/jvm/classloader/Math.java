/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.classloader;

/**
 * 类加载
 * @author study
 * @version : Math.java, v 0.1 2020年06月20日 23:28 study Exp $
 */
public class Math {
    public static int initData = 666;
    public static User user = new User();
    public int compute() {//一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a+b)*10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        int compute = math.compute();
        System.out.println("compute value is "+compute);

        user.setName("zhangsan");
        user.setAge(18);
        user.output();

        System.out.println("User test end");
    }
}