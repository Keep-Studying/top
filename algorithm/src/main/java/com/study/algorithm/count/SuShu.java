/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.count;

import org.junit.Test;

/**
 * SuShu
 *
 * @author boyan
 * @version : SuShu.java, v 0.1 2022-12-02 16:49 boyan
 */
public class SuShu {

    @Test public void test001() {
        int bf = bf(100);
        System.out.println(bf);
        int eratosthenes = eratosthenes(100);
        System.out.println(eratosthenes);
    }

    public static int bf(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }

    private static boolean isPrime(int x) {
        //for (int i = 2; i < x; i++) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 素数
     * 非素数(合数)：12 = 2 * 6
     *
     * @param n
     * @return
     */
    public static int eratosthenes(int n) {
        // false 表示是素数
        boolean[] isPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                count++;
                // j是合数的标记位置
                // 将合数标记为true，j = i*i是从2*i优化而来的，系数2会随着遍历递增j += i，
                // 相当于增加了系数2，每一个合数都会有两个比本身要小的因子(0,1)除外，2*i必然会遍历到这两个因子
                //当2递增到大雨根号n时，其实后面的已经无需再判断（或者只需要判断后面一段），从2到根号n，实际上
                // 在i递增的过程中已经计算过了，i实际上就相当于根号n
                for (int j = i * i; j  < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }
}
