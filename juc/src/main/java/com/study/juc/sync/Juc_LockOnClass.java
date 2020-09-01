/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.sync;

/**
 * @author study
 * @version : Juc_LockOnClass.java, v 0.1 2020年09月01日 20:18 study Exp $
 */
public class Juc_LockOnClass {

    static int stock;

    public static synchronized void decrStock(){
        System.out.println(--stock);
    }

    public static synchronized void cgg(){
        System.out.println();
    }

    public static void main(String[] args) {
        //Juc_LockOnClass.class对象
        Juc_LockOnClass.decrStock();
    }
}