/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import org.junit.Test;

/**
 * LeetCode136
 * 只出现一次的数字
 *
 * 常用的位运算
 * 按位与 & : 1&1 = 1  0&0 = 0  1&0 = 0
 * 按位或 | : 1|1 = 1  0|0 = 0  1|0 = 1
 * 按位非 ～ : ～1 = 0  ～0 = 1
 * 按位异或 ^ : 1^1 = 0  0^0 = 0 ,很明显任何一个数字和自己异或，结果一定是0
 * 有符号右移 >> : 若正数，高位补0，负数，高位补1
 * 有符号左移 << : 若正数，低位补0，负数，低位补1
 * 无符号右移 >>>: 不论正负，高位均补0
 * @author boyan
 * @version : LeetCode136.java, v 0.1 2022-12-27 17:18 boyan
 */
public class LeetCode136 {

    @Test
    public void test001(){
        int[] nums = new int[]{2,2,1};
        int singleNumber = singleNumber(nums);
        System.out.println(singleNumber);
    }

    public int singleNumber(int[] nums){
        int result = 0;
        for (int num:nums) {
            result = result ^ num;
        }
        return result;
    }
}