/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.utils;

/**
 * @author study
 * @version : SumUtils.java, v 0.1 2020年09月05日 15:36 study Exp $
 */
public class SumUtils {

    public static long sumRange(int[] arr, int lo, int hi) {
        long result = 0;

        for (int j = lo; j < hi; j++)
            result += arr[j];
        return result;
    }
}