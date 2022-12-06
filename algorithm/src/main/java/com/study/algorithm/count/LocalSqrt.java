/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.count;

import org.junit.Test;

/**
 * LocalSqrt
 *
 * 求x的平方根
 *
 * @author boyan
 * @version : LocalSqrt.java, v 0.1 2022-12-02 18:07 boyan
 */
public class LocalSqrt {

    @Test public void test001() {
        int search = binarySearch(25);
        System.out.println(String.format("search is %s", search));
    }

    public static int binarySearch(int x) {
        int index = -1, l = 0, r = x;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                index = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return index;
    }
}
