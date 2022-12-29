/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

/**
 * LeetCode11
 * 盛水最多的容器
 * @author boyan
 * @version : LeetCode11.java, v 0.1 2022-12-28 15:06 boyan
 */
public class LeetCode11 {

    public int maxArea(int[] height){
        int i = 0,j = height.length-1;
        int maxWater = 0;
        while (i < j){
            // height[i] 和 height[j] ，谁小就移动谁
            maxWater = height[i] < height[j] ? Math.max(maxWater,(j-i)* height[i++]):Math.max(maxWater,(j-i)* height[j--]);
        }
        return maxWater;
    }
}