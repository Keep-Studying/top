/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.tools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors中的各种方法
 * newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过护理需要，
 * 可灵活回收空闲线程；如果无可回收，则新建线程
 * newFixedThreadPool：创建一个定长线程池，可控制线程最大并发数，超出的线
 * 程会在队列中等待
 * newScheduledThreadPool：创建一个定长线程池，支持定时及周期性任务执行
 * newSingleThreadExecutor：创建一个单线程化的线程池，它会用唯一的工作
 * 线程来执行任务，保证所有任务按照指定顺序（FIFO，LIFO，优先级）执行
 *
 * 但阿里巴巴开发手册中，明确禁止使用Executors来创建线程池，了解即可
 * @author study
 * @version : ExecutorsSample.java, v 0.1 2020年08月23日 21:07 study Exp $
 */
public class ExecutorsSample {
    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

        //延迟三秒执行
        es.schedule(new Runnable() {
            public void run() {
                System.out.println("我在跑......");
            }
        },3, TimeUnit.SECONDS);

        es.shutdown();

    }
}