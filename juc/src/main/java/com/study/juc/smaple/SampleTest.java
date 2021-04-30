/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.juc.smaple;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author study
 * @version : SampleTest.java, v 0.1 2021年04月19日 23:11 study Exp $
 */
public class SampleTest {

    @Test
    public void test01() throws InterruptedException {
        AsyncQueueSample sample = new AsyncQueueSample();
        sample.init();
        for (int i = 0; i < 10; i++) {
            HashMap hashMap = new HashMap<String,Object>();
            hashMap.put("abc"+i,"abc"+i);
            sample.put(hashMap);
        }
        System.out.println(sample.isEmpty());
        Thread.sleep(1000);
    }
}