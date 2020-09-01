/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.sync;

/**
 * @author study
 * @version : Juc_Thread_Synchronize.java, v 0.1 2020年09月01日 20:20 study Exp $
 */
public class Juc_Thread_Synchronize {

    private final static Object object = new Object();

    public static void reentrantlock(){
        String tname = Thread.currentThread().getName();
        synchronized (object) {
            System.out.println(String.format("{}:) hold {}->monitor lock",tname,object));
            synchronized (object){
                System.out.println(String.format("{}:) re-hold {}->monitor lock",tname,object));
            }
        }
    }

    public static void main(String[] args)
    {
        Juc_Thread_Synchronize.reentrantlock();
    }
}