/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.utils;

import java.util.Random;

/**
 * @author study
 * @version : RandomUtils.java, v 0.1 2020年09月05日 14:59 study Exp $
 */
public class RandomUtils {

    /**
     * 构建指定size的int数组
     * @param size
     * @return
     * */
    public static int[] buildRandomIntArray(int size){
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

    /**
     * 构建随机size(0~100)的int数组
     * @return
     * */
    public static int[] buildRandomIntArray(){
        int size = new Random().nextInt(100);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

}