/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.count;

import java.util.Arrays;

/**
 * NumSum
 * 给定一个 升序排序的 整数数组numbers，从数组中找到两个满足相加之和等于目标数target
 * 假设每个输入只对应唯一的答案，且数组中数字不重复
 * 返回两数的下标值，以数组形式返回
 * @author boyan
 * @version : NumAscSum.java, v 0.1 2022-12-06 20:35 boyan
 */
public class NumAscSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1,2,3,4,5,6},10)));
        System.out.println(Arrays.toString(twoSearch(new int[]{1,2,3,4,5,6},10)));
        System.out.println(Arrays.toString(twoPoint(new int[]{1,2,3,4,5,6},10)));
    }

    public static int[] solution(int[] nums,int target) {
        for (int i = 0;i<nums.length;i++){
            for (int j = i+1;j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoSearch(int[] nums,int target) {
        for (int i = 0;i<nums.length;i++){
            int low = i, high = nums.length-1;
            while (low <= high){
                int mid = (high - low) / 2 + low;
                if (nums[mid] == target - nums[i] ){
                    return new int[]{i,mid};
                }else if (nums[mid] > target - nums[i]){
                    high = mid -1;
                } else {
                    low = mid +1 ;
                }
            }
        }
        return new int[0];
    }

    /**
     * 时间复杂度 ： T(n)
     * 空间复杂度 ： O(1)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoPoint(int[] nums,int target) {
        int low = 0, high = nums.length-1;
        while (low < high){
            int sum =  nums[low] + nums[high];
            if (sum == target){
                return new int[]{low,high};
            }else if (sum < target){
                low++;
            }else if (sum > target){
                high--;
            }
        }
        return new int[0];
    }
}
