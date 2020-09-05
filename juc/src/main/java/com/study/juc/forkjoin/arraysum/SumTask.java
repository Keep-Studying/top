/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.forkjoin.arraysum;

import com.study.juc.utils.SumUtils;

import java.util.concurrent.Callable;

/**
 * @author study
 * @version : SumTask.java, v 0.1 2020年09月05日 15:35 study Exp $
 */
public class SumTask implements Callable<Long> {

    int lo;
    int hi;
    int[] arr;

    public SumTask(int[] a, int l, int h) {
        lo = l;
        hi = h;
        arr = a;
    }

    public Long call() { //override must have this type
        //System.out.printf("The range is [%d - %d]\n", lo, hi);
        long result = SumUtils.sumRange(arr, lo, hi);
        return result;
    }
}