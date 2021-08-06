/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.spring.aop;

/**
 *
 * @author boyan
 * @version : Calculate.java, v 0.1 2021年07月15日 12:08 上午 boyan Exp $
 */
public interface Calculate {
    /**
     * 加法
     * @param numA
     * @param numB
     * @return
     */
    int add(int numA, int numB);

    /**
     * 减法
     * @param numA
     * @param numB
     * @return
     */
    int reduce(int numA, int numB);

    /**
     * 除法
     * @param numA
     * @param numB
     * @return
     */
    int div(int numA, int numB);

    /**
     * 乘法
     * @param numA
     * @param numB
     * @return
     */
    int multi(int numA, int numB);

    int mod(int numA, int numB);
}