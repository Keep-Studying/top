/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组类使用示例
 * @author study
 * @version : AtomicIntegerArrayRunner.java, v 0.1 2020年08月24日 21:16 study Exp $
 */
public class AtomicIntegerArrayRunner {
    static int[]              value   = new int[]{1,2};
    /**
     * 通过AtomicIntegerArray构造方法得出的数组，是原array的拷贝副本
     * {@Code
     * public AtomicIntegerArray(int[] array) {
     *    // Visibility guaranteed by final field guarantees
     *    this.array = array.clone();
     * }
     * }
     * */
    static AtomicIntegerArray aiArray = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        //todo 原子修改数组下标0的数值
        aiArray.getAndSet(0,3);
        System.out.println(aiArray.get(0));
        System.out.println(value[0]);
    }
}