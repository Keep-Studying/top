/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.unsafe;

import com.study.juc.utils.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * Unsafe内存屏障应用示例
 * {@link Unsafe#monitorEnter(java.lang.Object)}
 * {@link Unsafe#monitorExit(java.lang.Object)}
 * @author study
 * @version : ObjectMonitorRunner.java, v 0.1 2020年08月24日 21:59 study Exp $
 */
public class ObjectMonitorRunner {
    static Object object = new Object();
    static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    /**
     * 可以利用
     * sun.misc.Unsafe#monitorEnter(java.lang.Object)
     * sun.misc.Unsafe#monitorExit(java.lang.Object)
     * 跨方法进行加锁
     *
     * method1中加锁
     * method2中解锁
     * */
    public void method1(){
        unsafe.monitorEnter(object);
    }

    public void method2(){
        unsafe.monitorExit(object);
    }

    public static void main(String[] args) {
        //jvm内置锁
        synchronized (object){

            //写逻辑
        }
    }
}