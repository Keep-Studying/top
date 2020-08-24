/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic原子类进行原子更新时，可能出现ABA问题的示例
 * @author study
 * @version : AtomicAbaProblemRunner.java, v 0.1 2020年08月24日 21:09 study Exp $
 */
@Slf4j
public class AtomicAbaProblemRunner {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread main = new Thread(()->{
                int a = atomicInteger.get();
                log.info("操作线程"+Thread.currentThread().getName()+"--修改前操作数值:"+a);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isCasSuccess = atomicInteger.compareAndSet(a,2);
                if(isCasSuccess){
                    log.info("操作线程"+Thread.currentThread().getName()+"--Cas修改后操作数值:"+atomicInteger.get());
                }else{
                    log.info("CAS修改失败");
                }
        }, "主线程");
        Thread other = new Thread(() -> {
            atomicInteger.incrementAndGet();// 1+1 = 2;
            log.info("操作线程"+Thread.currentThread().getName()+"--increase后值:"+atomicInteger.get());
            atomicInteger.decrementAndGet();// atomic-1 = 2-1;
            log.info("操作线程"+Thread.currentThread().getName()+"--decrease后值:"+atomicInteger.get());
        }, "干扰线程");

        main.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        other.start();
    }
}