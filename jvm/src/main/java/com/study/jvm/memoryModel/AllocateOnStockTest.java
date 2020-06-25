/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.memoryModel;

import com.study.jvm.classloader.User;

/**
 * 栈上分配，标量替换
 * 代码调用了1亿次allocate()，如果是分配到堆上，大概需要1GB以上堆空间，
 * 如果堆空间小于该值，必然会触发GC
 *
 * 使用如下参数不会发生GC
 * -Xmx15m -Xmx15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 *
 * 使用如下参数都会发生大量GC
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * @author study
 * @version : AllocateOnStockTest.java, v 0.1 2020年06月25日 11:29 study Exp $
 */
public class AllocateOnStockTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            allocate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void allocate(){
        User user = new User();
        user.setAge(1);
        user.setName("zhangsan");
    }
}