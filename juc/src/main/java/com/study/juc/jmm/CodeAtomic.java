/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.jmm;

/**
 * 原子性问题
 *
 * 原子性指的是一个操作是不可中断的，即使是在多线程环境下，一个操作一旦
 * 开始就不会被其他线程影响。
 *
 * 除了JVM自身提供的对基本数据类型读写操作的原子性外，可以通过synchronized
 * 和Lock实现原子性。因为synchronized和Lock能够保证任一时刻只有一个线程
 * 访问该代码块。
 * @author study
 * @version : CodeAtomic.java, v 0.1 2020年08月11日 0:21 study Exp $
 */
public class CodeAtomic {

    private volatile static int counter = 0;
    static Object object = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    synchronized (object){
                        counter++;//分三步- 读，自加，写回
                    }
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }
}