/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.sync;

/**
 * @author study
 * @version : Juc_LockAppend.java, v 0.1 2020年09月01日 20:19 study Exp $
 */
public class Juc_LockAppend {

    StringBuffer stb = new StringBuffer();

    Object object = new Object();

    //锁的消除
    private void method1(){
        Object object1 = new Object();

        synchronized (object1){
            //
            //sdf
            //asdf
            System.out.println();
        }

    }

    private void method(){
        /*stb.append("杨过");
        stb.append("小龙女");
        stb.append("大雕");
        stb.append("郭靖");*/

        synchronized (object){
            System.out.println("");

            System.out.println("");

            System.out.println("");
        }

        /*synchronized (object){
            System.out.println("");
        }

        synchronized (object){
            System.out.println("");
        }*/

    }
}