/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author study
 * @version : MapTest.java, v 0.1 2020年08月30日 16:12 study Exp $
 */
public class MapTest {

    public static void main(String[] args) {
        MapResizer map = new MapResizer();
        for (int i=0;i<30;i++){
            new Thread(new MapResizer()).start();
        }

    }
}

class MapResizer implements Runnable {
    private static Map<Integer,Integer> map = new HashMap<Integer, Integer>(2);

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public void run() {
        while(atomicInteger.get() < 100000){
            map.put(atomicInteger.get(),atomicInteger.get());
            atomicInteger.incrementAndGet();
        }
    }
}