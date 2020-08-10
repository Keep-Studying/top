/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * 可见性问题
 *
 * 可见性指的是当一个线程修改了某个共享变量的值，其他线程是否能够马上得知这个修改的值
 *
 * volatile关键字保证可见性。当一个共享变量被volatile修饰时，它会保证修改的值
 * 立即被其他的线程看到，即修改的值立即更新到主存中，当其他线程需要读取时，它会
 * 去内存中读取新值。
 *
 * 注意：
 * volatile，可以解决可见性(能否及时看到)；
 * 不加volatile关键字也能看到，但不保证及时性
 * @author study
 * @version : CodeVisibility.java, v 0.1 2020年08月11日 0:24 study Exp $
 */
@Slf4j
public class CodeVisibility {

    private static boolean initFlag = false;

    private volatile static int counter = 0;
    public static void refresh(){
        log.info("refresh data.......");
        initFlag = true;
        log.info("refresh data success.......");
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (!initFlag){
                //System.out.println("runing");
                counter++;
            }
            log.info("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变");
        },"threadA");
        threadA.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(()->{
            refresh();
        },"threadB");
        threadB.start();
    }
}