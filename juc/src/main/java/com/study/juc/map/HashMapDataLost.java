/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author study
 * @version : HashMapDataLost.java, v 0.1 2020年08月30日 16:03 study Exp $
 */
public class HashMapDataLost {

    public static final Map<String, String> map = new HashMap<String, String>();
    public static void main(String[] args) throws InterruptedException {
        //线程一
        new Thread() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
            }
        }.start();
        //线程二
        new Thread(){
            public void run() {
                for(int j=1000;j<2000;j++){
                    map.put(String.valueOf(j), String.valueOf(j));
                }
            }
        }.start();

        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("map.size"+map.size());
        //输出
        for(int i=0;i<2000;i++){
            System.out.println("第："+i+"元素，值："+map.get(String.valueOf(i)));
        }

    }
}