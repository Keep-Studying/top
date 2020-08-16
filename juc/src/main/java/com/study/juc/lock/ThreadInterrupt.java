/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version : ThreadInterrupt.java, v 0.1 2020年08月17日 7:36 study Exp $
 */
@Slf4j
public class ThreadInterrupt {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void reentrantLock() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        boolean flag = false;
        //可以使用lock.lockInterruptibly();方法进行加锁，但需要抛出InterruptedException异常
        //lock.lockInterruptibly();
        lock.lock();
        log.info("Thread:{},加锁成功!",threadName);
        while(true){
            if(Thread.interrupted()){
                break;
            }
            //逻辑,批处理数据
            //逻辑
        }
        lock.unlock();
        log.info("Thread:{},锁退出同步块",threadName);
    }

    public static void main(String[] args) {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t0");
        t0.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //异常处理
                }
            }
        },"t1");
        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();

    }
}