/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode338
 * 比特位计数
 * 给一个整数n，对于0<=i<=n中的每个i，计算其二进制中1的个数，返回一个长度
 * 为n+1的数组作为答案
 * @author boyan
 * @version : LeetCode338.java, v 0.1 2022-12-27 17:31 boyan
 */
public class LeetCode338 {

    @Test
    public void test01(){
        int[] ints = countBits(5);
        System.out.println(Arrays.toString(ints));
        int[] ints2 = countBits2(5);
        System.out.println(Arrays.toString(ints2));
    }

    /**
     * 利用位运算来解决
     * i&(i-1) 消除低位置的1
     * @param num
     * @return
     */
    public int[] countBits(int num){
        int[] bits = new int[num+1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i&(i-1)]+1;
        }
        return bits;
    }

    /**
     * 利用奇偶性来解决
     * @param num
     * @return
     */
    public int[] countBits2(int num){
        int[] bits = new int[num+1];
        bits[0] = 0;
        for (int i = 1; i <= num; i++) {
            bits[i] = (i&1) == 1 ? bits[i-1]+1:bits[i>>1];
        }
        return bits;
    }
}