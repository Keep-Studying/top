/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.forkjoin.arraysum;

import com.study.juc.utils.RandomUtils;
import com.study.juc.utils.SumUtils;

/**
 * @author study
 * @version : SumSequential.java, v 0.1 2020年09月05日 15:38 study Exp $
 */
public class SumSequential {
    public static long sum(int[] arr){
        return SumUtils.sumRange(arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = RandomUtils.buildRandomIntArray();
        System.out.printf("The array length is: %d\n", arr.length);

        long result = sum(arr);

        System.out.printf("The result is: %d\n", result);
    }

}