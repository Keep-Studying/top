/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.jmm;

/**
 * volatile关键字的可见性示例
 * @author study
 * @version : VolatileVisibilitySample.java, v 0.1 2020年08月11日 0:42 study Exp $
 */
public class VolatileVisibilitySample {

    public volatile boolean initFlag = false;
    public void save(){
        this.initFlag = true;
        String threadname = Thread.currentThread().getName();
        System.out.println("线程："+threadname+":修改共享变量initFlag");
    }
    public void load(){
        String threadname = Thread.currentThread().getName();
        while (!initFlag){
            //线程在此处空跑，等待initFlag状态改变
        }
        System.out.println("线程："+threadname+"当前线程嗅探到initFlag的状态的改变");
    }
    public static void main(String[] args){
        VolatileVisibilitySample sample = new VolatileVisibilitySample();
        Thread threadA = new Thread(()->{
            sample.save();
        },"threadA");
        Thread threadB = new Thread(()->{
            sample.load();
        },"threadB");
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }
}