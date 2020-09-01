/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.sync;

/**
 * @author study
 * @version : Juc_LockOnObject.java, v 0.1 2020年09月01日 20:18 study Exp $
 */
public class Juc_LockOnObject {

    public static Object object = new Object();

    private Integer stock = 10;

    public void decrStock(){
        //T1,T2
        synchronized (object){
            --stock;
            if(stock <= 0){
                System.out.println("库存售罄");
                return;
            }
        }
    }
}