/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.jmm;

import com.study.juc.utils.UnsafeInstance;
import lombok.extern.slf4j.Slf4j;

/**
 * 指令重排问题
 *
 * ava语言规范规定JVM线程内部维持顺序化语义。即只要程序的最终结果与它
 * 顺序化情况的结果相等，那么指令的执行顺序可以与代码顺序不一致，此过程
 * 叫指令的重排序。
 *
 * 指令重排序的意义是什么？JVM能根据处理器特性（CPU多级缓存系统、多核
 * 处理器等）适当的对机器指令进行重排序，使机器指令能更符合CPU的执行特
 * 性，最大限度的发挥机器性能。
 * @author study
 * @version : CodeReorder.java, v 0.1 2020年08月11日 0:31 study Exp $
 */
@Slf4j
public class CodeReorder {

    /**添加volatile关键字可以禁止指令重排*/
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    /**
     * 输出结果（未加volatile关键字和内存屏障时）：
     * ...
     * 00:36:33.002 [main] INFO com.study.juc.jmm.CodeReorder - 第806次 (0,1）
     * 00:36:33.002 [main] INFO com.study.juc.jmm.CodeReorder - 第807次 (0,1）
     * 第808次 (0,0）
     * */
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;){
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    //由于线程one先启动，下面这句话让它等一等线程two. 可根据自己电脑的实际性能适当调整等待
                    shortWait(1000);
                    a = 1;//是读还是写？store，volatile写
                    //storeload ,读写屏障，不允许volatile写与第二步volatile读发生重排

                    x = b;// 读还是写？读写都有，先读volatile，写普通变量
                    //分两步进行，第一步先volatile读，第二步再普通写

                    //需要手动加内存屏障(不允许使用volatile关键字时)
                    UnsafeInstance.reflectGetUnsafe().fullFence();
                }
            });

            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    UnsafeInstance.reflectGetUnsafe().fullFence();
                    y = a;
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            /**
             * 不考虑指令重排，只会存在下面三种结果
             * 1,1
             * 0,1
             * 1,0
             * cpu或者jit对我们的代码进行了指令重排的话，会出现下面这种情况
             * 0,0
             */
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if(x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                log.info(result);
            }
        }

    }

    /**
     * 等待一段时间，时间单位纳秒
     * @param interval
     */
    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }
}