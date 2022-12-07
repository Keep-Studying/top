/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.dynamicprogram;

/**
 * MaxSeq
 * 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度
 *
 * 序列的下标是连续的
 * @author boyan
 * @version : MaxSeq.java, v 0.1 2022-12-07 16:30 boyan
 */
public class MaxSeq {

    public static void main(String[] args) {
        System.out.println(findLength(new int[]{1,2,3,2,3,4,3,4,5,6,7}));
    }

    public static int findLength(int[] nums){
        int start = 0;
        int max = 0;
        for (int i = 1;i <nums.length;i++){
            if(nums[i] <= nums[i-1]){
                start = i;
            }
            max =  Math.max(max,i-start+1);
        }
        for(int j = start;j < start+max;j++){
            System.out.print(nums[j]);
        }
        System.out.println();
        return max;
    }


}