/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.fiber;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 协程	(纤程，用户级线程)，目的是为了追求最大力度的发挥硬件性能和提升软件的速度，
 * 协程基本原理是:在某个点挂起当前的任务，并且保存栈信息，去执行另一个任务；等完
 * 成或达到某个条件时，再还原原来的栈信息并继续执行(整个过程线程不需要上下文切换)。
 *
 * ps:Java原生不支持协程，在纯java代码里需要使用协程的话需要引入第三方包,如：quasar
 *
 * Java开发中一般用不到，了解即可
 * @author study
 * @version : QuasarFiberRunner.java, v 0.1 2020年09月01日 23:52 study Exp $
 */
@Slf4j
public class QuasarFiberRunner {
    @Suspendable
    static void m1() throws InterruptedException, SuspendExecution {
        String m = "m1";
        //log.info("m1 begin");
        m = m2();
        //log.info("m1 end");
    }

    static String m2() throws SuspendExecution, InterruptedException {
        String m = m3();
        Strand.sleep(1000);
        return m;
    }

    @Suspendable
    static String m3() {
        List l = Stream.of(1,2,3).filter(i -> i%2 == 0).collect(Collectors.toList());
        return l.toString();
    }
    static public void main(String[] args) throws ExecutionException, InterruptedException {
        int count = 10000;
        testThreadpool(count);
        testFiber(count);
    }
    static void testThreadpool(int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        ExecutorService es = Executors.newFixedThreadPool(200);
        LongAdder latency = new LongAdder();
        long t = System.currentTimeMillis();
        for (int i =0; i< count; i++) {
            es.submit(() -> {
                long start = System.currentTimeMillis();
                try {
                    m1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SuspendExecution suspendExecution) {
                    suspendExecution.printStackTrace();
                }
                start = System.currentTimeMillis() - start;
                latency.add(start);
                latch.countDown();
            });
        }
        latch.await();
        t = System.currentTimeMillis() - t;
        long l = latency.longValue() / count;
        System.out.println("thread pool took: " + t + ", latency: " + l + " ms");
        es.shutdownNow();
    }

    static void testFiber(int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        LongAdder latency = new LongAdder();
        long t = System.currentTimeMillis();
        for (int i =0; i< count; i++) {
            new Fiber<Void>("Caller", new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    long start = System.currentTimeMillis();
                    m1();
                    start = System.currentTimeMillis() - start;
                    latency.add(start);
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        t = System.currentTimeMillis() - t;
        long l = latency.longValue() / count;
        System.out.println("fiber took: " + t  + ", latency: " + l + " ms");
    }
}