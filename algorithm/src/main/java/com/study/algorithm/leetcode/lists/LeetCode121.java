/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * LeetCode121
 * 买卖股票的最佳时机
 * @author boyan
 * @version : LeetCode121.java, v 0.1 2022-12-28 14:17 boyan
 */
public class LeetCode121 {

    @Test
    public void test001(){
        int[] nums = new int[]{7,1,5,3,6,4};

        int maxProfit = maxProfit(nums);
        System.out.println(maxProfit);
    }

    public int maxProfit(int[] prices){
        if (prices.length < 2){
            return 0;
        }
        int profitMax = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 当前元素大于最小值，看是否需要更新已知的最大差距
            if (prices[i] > min){
                profitMax = Math.max(profitMax,prices[i]-min);
            }else {
                // 当前元素小于等于最小值，则更新当前最小值
                min = prices[i];
            }
        }
        return profitMax;
    }
}