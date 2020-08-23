/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏屏障，让一组线程到达一个屏障（也可以叫做同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有并屏障拦截的线程才会继续运行。
 *
 * CountDownLatch也可以起到和CyclicBarrier一样的效果，
 * {@Code
 *        CountDownLatch countDownLatch = new CountDownLatch(1);
 *         for (int i = 0; i < 10; i++) {
 *             new Thread(()->{
 *                 try {
 *                     countDownLatch.await();
 *                 } catch (InterruptedException e) {
 *                     e.printStackTrace();
 *                 }
 *             },"index:"+i).start();
 *         }
 *         countDownLatch.countDown();
 * }
 * 区别是：
 * countDownLatch是只能使用一次的
 * 而cyclicBarrier是可以使用多次的
 * @author study
 * @version : CyclicBarrierSample.java, v 0.1 2020年08月23日 20:09 study Exp $
 */
@Slf4j
public class CyclicBarrierSample {
    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(11, new Runnable() {
            @Override
            public void run() {
                log.info("所有特工到达屏障，准备开始执行秘密任务");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierRunner(cyclicBarrier,i)).start();
        }
        cyclicBarrier.await();
        log.info("全部到达屏障.....");
    }
}

@Slf4j
class CyclicBarrierRunner implements Runnable{
    private CyclicBarrier cyclicBarrier;
    private int index ;

    public CyclicBarrierRunner(CyclicBarrier cyclicBarrier, int index) {
        this.cyclicBarrier = cyclicBarrier;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            log.info("index:"+index);
            index--;
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}