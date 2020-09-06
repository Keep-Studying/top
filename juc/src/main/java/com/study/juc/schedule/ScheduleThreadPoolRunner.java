/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.schedule;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version : ScheduleThreadPoolRunner.java, v 0.1 2020年09月04日 0:55 study Exp $
 */
@Slf4j
public class ScheduleThreadPoolRunner {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("我要延迟5s执行");
            return 1;
        }, 5000, TimeUnit.MILLISECONDS);

        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("我要延迟10s执行");
            return 1;
        }, 10000, TimeUnit.MILLISECONDS);

        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("我要延迟3s执行");
            return 1;
        }, 3000, TimeUnit.MILLISECONDS);

        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("我要延迟1s执行");
            return 1;
        }, 1000, TimeUnit.MILLISECONDS);
        //提交任务的线程-接着干活
        //xxxxx
        //xxxx
        //xxxx
        /*try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


        /**
         * 输出结果(需要等该任务执行成功后，才开始计算时间，往后延迟指定时间再执行下一个周期性任务)：
         * 15:56:38.195 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * 15:56:47.196 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - task over....
         * 15:56:49.197 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * 15:56:58.198 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - task over....
         * 15:57:00.198 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * 15:57:09.200 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - task over....
         * 15:57:11.201 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * */
        //发心跳，service1->service2,每次过5s，发送一个心跳，证明s2可用
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            log.info("send heart beat");
            long starttime = System.currentTimeMillis(), nowtime = starttime;
            while ((nowtime - starttime) < 5000) {
                nowtime = System.currentTimeMillis();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("task over....");
            //抛出异常后，该线程就无法继续工作了
            //throw new RuntimeException("unexpected error , stop working");
        }, 1000, 2000, TimeUnit.MILLISECONDS);

        /**
         * 输出结果(不管当前任务是否执行成功，按照任务提交的时间往后延迟指定时间)：
         * 15:53:41.034 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * 15:53:46.160 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - task over....
         * 15:53:46.160 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * 15:53:51.289 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - task over....
         * 15:53:51.289 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * 15:53:56.411 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - task over....
         * 15:53:56.411 [pool-1-thread-1] INFO com.study.juc.schedule.ScheduleThreadPoolRunner - send heart beat
         * */
        /*scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            log.info("send heart beat");
            long starttime = System.currentTimeMillis(), nowtime = starttime;
            while ((nowtime - starttime) < 5000) {
                nowtime = System.currentTimeMillis();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("task over....");
            //抛出异常后，该线程就无法继续工作了
            //throw new RuntimeException("unexpected error , stop working");
        }, 1000, 2000, TimeUnit.MILLISECONDS);*/

        //定时类
        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("send heart beat");
                throw new RuntimeException("unexpected error , stop working");
            }
        },1000,2000);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("send heart beat");
                throw new RuntimeException("unexpected error , stop working");
            }
        },1000,2000);*/

        /*scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
            log.info("running...");
        },1000,2000,TimeUnit.MILLISECONDS);*/

    }
}