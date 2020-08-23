/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.tools;

import java.util.concurrent.Semaphore;

/**
 * Semaphore字面意思是信号量的意思，它的作用是控制访问特定资源的线程数目，
 * 底层依赖AQS的状态state，是在生产当中比较常用的一个工具类
 * @author study
 * @version : SemaphoreSample.java, v 0.1 2020年08月23日 19:58 study Exp $
 */
public class SemaphoreSample {
    /**
     * 结果打印
     * Thread-1:aquire() at time:1597907850955
     * Thread-0:aquire() at time:1597907850955
     * Thread-0:release() at time:1597907851956
     * Thread-3:aquire() at time:1597907851956
     * Thread-2:aquire() at time:1597907851956
     * Thread-1:release() at time:1597907851956
     * Thread-4:aquire() at time:1597907852956
     * Thread-2:release() at time:1597907852958
     * Thread-3:release() at time:1597907852959
     * Thread-4:release() at time:1597907853956
     *
     * 打印结果可以看出，一次只有两个线程执行acquire()，
     * 只有线程进行release()方法后才会有别的线程执行acquire()
     * */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore,"zhangsan"+i)).start();
        }
    }

    static class Task extends Thread{
        Semaphore semaphore;

        public Task(Semaphore semaphore, String name) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+":aquire() at time:"+System.currentTimeMillis());
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+":release() at time:"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}