/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * LeetCode53
 * 最大子序之和
 * @author boyan
 * @version : LeetCode53.java, v 0.1 2022-12-28 13:50 boyan
 */
public class LeetCode53 {


    @Test
    public void test001(){
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int subArray1 = maxSubArray1(nums);
        System.out.println(subArray1);

        int subArray = maxSubArray(nums);
        System.out.println(subArray);
    }


    /**
     * 动态规划 需要数组
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // 状态转移公式
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            if (dp[i] > result){
                result = dp[i];
            }
        }
        return result;
    }


    /**
     * 动态规划 不需要数组
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums){
        int result = nums[0];
        int pre = result;
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i],nums[i]);
            if (pre > result){
                result = pre;
            }
        }
        return result;
    }
}