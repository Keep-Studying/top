/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLaunch这个类能够使一个线程等待其他线程完成各自的工作后再执行
 * 比如陪媳妇去看病。
 * 医院里边排队的人很多，如果一个人的话，要先看大夫，看完大夫再去排队交钱取药。
 * 现在我们是双核，可以同时做这两个事（多线程）。
 * 假设看大夫花3秒钟，排队交费取药花5秒钟。我们同时搞的话，5秒钟我们就能完成，
 * 然后一起回家（回到主线程）
 * @author study
 * @version : CountDownLaunchSample.java, v 0.1 2020年08月23日 20:00 study Exp $
 */
@Slf4j
public class CountDownLaunchSample {
    /**
     * 配媳妇去看病，轮到媳妇看大夫时，我就开始去排队准备交钱了
     *
     * 结果打印：
     * 开始看医生
     * 开始在医院药房排队买药.....
     * 看医生结束，准备离开病房
     * 排队成功，可以开始缴费买药
     * over，回家 cost:5005
     * */
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();

        countDownLatch.await();
        System.out.println("over，回家 cost:"+(System.currentTimeMillis()-now));
        //log.info("over，回家 cost:"+(System.currentTimeMillis()-now));
    }

    /**
     * 先使用countDownLatch.await();
     * 再使用countDownLatch.countDown();
     * 会起到CyclicBarrier类型的效果
     * */
    private void test01(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"index:"+i).start();
        }
        countDownLatch.countDown();
    }
}

/**
 * 看医生任务
 * */
@Slf4j
class SeeDoctorTask implements Runnable{

    private CountDownLatch countDownLatch;

    public SeeDoctorTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            //log.info("开始看医生");
            System.out.println("开始看医生");
            Thread.sleep(3000);
            //log.info("看医生结束，准备离开病房");
            System.out.println("看医生结束，准备离开病房");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(countDownLatch != null){
                countDownLatch.countDown();
            }
        }
    }
}

/**
 * 排队任务
 * */
@Slf4j
class QueueTask implements Runnable{

    private CountDownLatch countDownLatch;

    public QueueTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            //log.info("开始在医院药房排队买药.....");
            System.out.println("开始在医院药房排队买药.....");
            Thread.sleep(5000);
            //log.info("排队成功，可以开始缴费买药");
            System.out.println("排队成功，可以开始缴费买药");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(countDownLatch != null){
                countDownLatch.countDown();
            }
        }
    }
}