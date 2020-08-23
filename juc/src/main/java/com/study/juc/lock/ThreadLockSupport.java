/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport用法
 * LockSupport.park();
 * LockSupport.unpark(thread);
 * @author study
 * @version : ThreadLockSupport.java, v 0.1 2020年08月17日 7:18 study Exp $
 */
@Slf4j
public class ThreadLockSupport {
    public static void main(String[] args) {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread current = Thread.currentThread();
                log.info("{},开始执行",current.getName());
                for (;;) {//spin自旋
                    log.info("准备park住当前线程：{}....",current.getName());
                    LockSupport.park();
                    Thread.interrupted();//会清除线程上的中断标记，让线程重新被park住
                    log.info("当前线程{}已经被唤醒",current.getName());
                }
            }
        }, "t0");
        t0.start();
        try {
            Thread.sleep(2000);
            log.info("准备唤醒{}线程!",t0.getName());
            LockSupport.unpark(t0);
            Thread.sleep(2000);
            t0.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}