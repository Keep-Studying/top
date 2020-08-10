/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.jmm;

/**
 * 内存屏障示例
 * @author study
 * @version : VolatileBarrierExample.java, v 0.1 2020年08月11日 0:50 study Exp $
 */
public class VolatileBarrierExample {

    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;
    void readAndWrite() {
        int i = v1;// 第一个volatile读
        int j = v2;// 第二个volatile读
        a = i + j; // 普通写
        v1 = i + 1;// 第一个volatile写
        v2 = j * 2;// 第二个 volatile写
    }
}