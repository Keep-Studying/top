/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * AtomicStampedReference：原子更新带有版本号的引用类型。
 *
 * 该类将整数值与引用关联起来，可用于原子的更新数据和数据的版本号，
 * 可以解决使用CAS进行原子更新时，可能出现的ABA问题。
 * @author study
 * @version : AtomicStampedReferenceRunner.java, v 0.1 2020年08月24日 21:40 study Exp $
 */
public class AtomicStampedReferenceRunner {

    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(1, 0);

    /**
     * 结果打印：
     * 操作线程Thread[主操作线程,5,main]stamp=0,初始值 a = 1
     * 操作线程Thread[干扰线程,5,main]stamp=1,【increment】 ,值 a= 2
     * 操作线程Thread[干扰线程,5,main]stamp=2,【decrement】 ,值 a= 1
     * 操作线程Thread[主操作线程,5,main]stamp=0,CAS操作结果: false
     * */
    public static void main(String[] args){
        Thread main = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
            System.out.println("操作线程" + Thread.currentThread()+ "stamp="+stamp + ",初始值 a = " + atomicStampedRef.getReference());
            try {
                Thread.sleep(3000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1,2,stamp,stamp +1);
            System.out.println("操作线程" + Thread.currentThread() + "stamp="+stamp + ",CAS操作结果: " + isCASSuccess);
        },"主操作线程");

        Thread other = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp();
            atomicStampedRef.compareAndSet(1,2,stamp,stamp+1);
            System.out.println("操作线程" + Thread.currentThread() + "stamp="+atomicStampedRef.getStamp() +",【increment】 ,值 a= "+ atomicStampedRef.getReference());
            stamp = atomicStampedRef.getStamp();
            atomicStampedRef.compareAndSet(2,1,stamp,stamp+1);
            System.out.println("操作线程" + Thread.currentThread() + "stamp="+atomicStampedRef.getStamp() +",【decrement】 ,值 a= "+ atomicStampedRef.getReference());
        },"干扰线程");

        main.start();
        LockSupport.parkNanos(1000000);
        other.start();
    }
}