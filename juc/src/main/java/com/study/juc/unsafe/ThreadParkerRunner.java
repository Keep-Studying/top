/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.unsafe;

import com.study.juc.utils.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * {@link Unsafe#park(boolean, long)}和{@link Unsafe#unpark(java.lang.Object)}应用示例
 * @author study
 * @version : ThreadParkerRunner.java, v 0.1 2020年08月24日 22:01 study Exp $
 */
public class ThreadParkerRunner {
    static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread - is running----");
                //true则会实现ms定时,false则会实现ns定时。
                unsafe.park(false,0L); //阻塞当前线程
                System.out.println("thread is over-----");
            }
        });

        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("唤醒Thread-t");
        unsafe.unpark(t);

    }
}