/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import org.junit.Test;

/**
 * LeetCode461
 * 汉明距离
 * 两个整数之间的汉明距离指的是两个数字对应二进制位不同的位置的数目
 * @author boyan
 * @version : LeetCode461.java, v 0.1 2022-12-27 17:44 boyan
 */
public class LeetCode461 {

    @Test
    public void test001(){
        int distance = hanMingDistance(1, 4);
        System.out.println(distance);
    }

    public int hanMingDistance(int x,int y){
        int distance = 0;
        for (int xor = x ^y ; xor != 0; xor &= xor-1){
            distance++;
        }
        return distance;
    }
}