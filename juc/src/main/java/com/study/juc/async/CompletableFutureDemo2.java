/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.juc.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureDemo2
 *
 * @author Lenovo
 * @version : CompletableFutureDemo2.java, v 0.1 2023-02-14 16:46 Lenovo
 */
public class CompletableFutureDemo2 {

    public static void main(String[] args) {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1:洗水壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1:烧开水...");
            sleep(15, TimeUnit.SECONDS);
        });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2:洗茶杯...");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2:拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        //等待任务3执行结果
        System.out.println(cf3.join());
    }


    public static void sleep(long time, TimeUnit timeUnit){
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}