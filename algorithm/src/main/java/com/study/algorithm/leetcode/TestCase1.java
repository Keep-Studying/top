/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TestCase1
 *
 * @author boyan
 * @version : TestCase1.java, v 0.1 2023-02-06 14:23 boyan
 */
public class TestCase1 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(4, 10, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        CountDownLatch countDownLatch = new CountDownLatch(4);
        int batchSize = 250;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // 0~250
        // 251~500
        // 501~750
        for (int i = 1; i <= 4; i++) {
            int le = (i-1)*batchSize+1;
            int rt = i*batchSize;
            System.out.println(String.format("begin is %s , end is %s ",le,rt));
            LocalRunnable localRunnable = new LocalRunnable(le, rt, countDownLatch,atomicInteger);
            threadPoolExecutor.execute(localRunnable);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("the count is %s",atomicInteger.get()));
    }


    static class LocalRunnable implements Runnable{
        int begin;
        int end;
        int result;
        CountDownLatch countDownLatch;
        AtomicInteger atomicInteger;
        public LocalRunnable(int begin, int end,CountDownLatch countDownLatch,AtomicInteger atomicInteger) {
            this.begin = begin;
            this.end = end;
            this.countDownLatch = countDownLatch;
            this.atomicInteger = atomicInteger;
            this.result = 0;
        }
        @Override public void run() {
            System.out.println(String.format("thread id is %s , begin is %s , end is %s ",Thread.currentThread(),begin,end));
            for (int i = begin; i <= end; i++) {
                result += i;
            }
            System.out.println(String.format("thread id is %s , result is %s ",Thread.currentThread(),result));
            atomicInteger.getAndAdd(result);
            System.out.println(String.format("thread id is %s , atomicInteger is %s ",Thread.currentThread(),atomicInteger.get()));
            countDownLatch.countDown();
        }

        /**
         * Getter method for property <tt>result</tt>.
         *
         * @return property value of result
         */
        public int getResult() {
            return result;
        }
    }
}