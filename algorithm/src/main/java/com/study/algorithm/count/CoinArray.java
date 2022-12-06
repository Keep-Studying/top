/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.count;

/**
 * CoinArray
 *
 * @author boyan
 * @version : CoinArray.java, v 0.1 2022-12-06 21:29 boyan
 */
public class CoinArray {

    public static void main(String[] args) {
        System.out.println(coinsArray(10));
        System.out.println(coinsArray2(10));
    }

    /**
     * 迭代的算法
     *
     * @param n
     * @return
     */
    public static int coinsArray(int n){
        for (int i = 1;i<= n;i++){
            n = n-i;
            if (n < i){
                return i;
            }
        }
        return 0;
    }


    /**
     * 二分查找
     *
     * @param n
     * @return
     */
    public static int coinsArray2(int n){
        int low = 0,high = n;
        while (low <= high){
            int mid = (high - low)/2 + low;
            int cost = ((mid +1) * mid )/ 2;
            if (cost == n){
                return mid;
            }else if (cost > n){
                high = mid -1;
            }else {
                low = mid + 1;
            }
        }
        return high;
    }
}
