/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode204
 * 计数质数
 *
 * https://leetcode.cn/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
 * @author boyan
 * @version : LeetCode204.java, v 0.1 2023-01-12 00:02 boyan
 */
public class LeetCode204 {

    @Test
    public void test001(){
        int i = countPrimes(10);
        System.out.println(i);
    }


    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime,1);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1){
                ans += 1;
                if ((long) i * i < n){
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}