/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author study
 * @version : MapDeadLock.java, v 0.1 2020年09月01日 8:24 study Exp $
 */
public class MapDeadLock {

    public static void main(String[] args) {
        for (int i=0; i<10; i++){
            new Thread(new MultiThread()).start();
        }
    }
}

class MultiThread implements Runnable {
    private static Map<Integer,Integer> map = new ConcurrentHashMap<>(11);

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public void run() {
        while(atomicInteger.get() < 1000000){
            map.put(atomicInteger.get(),atomicInteger.get());
            atomicInteger.incrementAndGet();
        }
    }
}