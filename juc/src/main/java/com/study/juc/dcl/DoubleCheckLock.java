/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.dcl;

/**
 * 双重校验锁保证单例的安全性（禁止指令重排）
 * @author study
 * @version : DoubleCheckLock.java, v 0.1 2020年08月11日 0:46 study Exp $
 */
public class DoubleCheckLock {

    /**
     * 查看汇编指令
     * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
     */
    private volatile static DoubleCheckLock instance;
    private DoubleCheckLock(){}
    /**
     * 双重锁机制保证单例安全
     * @return
     */
    public static DoubleCheckLock getInstance(){
        //第一次检测
        if (instance==null){
            //同步
            synchronized (DoubleCheckLock.class){
                if (instance == null){
                    //多线程环境下可能会出现问题的地方
                    instance = new  DoubleCheckLock();

                    //因为instance = new DoubleCheckLock();
                    //可以分为以下3步完成(伪代码)
                    /*memory = allocate();//1.分配对象内存空间
                    instance(memory);//2.初始化对象
                    //3.设置instance指向刚分配的内存地址，此时instance！=null
                    instance = memory;*/
                }
            }
        }
        return instance;
    }
}