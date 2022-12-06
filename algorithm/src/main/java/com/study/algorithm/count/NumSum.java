/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.count;

import java.util.Arrays;
import java.util.HashMap;

/**
 * NumSum
 * 给定一个整数数组numbers，从数组中找到两个满足相加之和等于目标数target
 * 假设每个输入只对应唯一的答案，且数组中数字不重复
 * 返回两数的下标值，以数组形式返回
 * @author boyan
 * @version : NumSum.java, v 0.1 2022-12-06 20:23 boyan
 */
public class NumSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1,2,3,4,5,6},10)));
        System.out.println(Arrays.toString(solution1(new int[]{1,2,3,4,5,6},10)));
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

    public static int[] solution1(int[] nums,int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
           if (hashMap.containsKey(target -nums[i])){
               return new int[]{hashMap.get(target-nums[i]),i};
           }
            hashMap.put(nums[i],i);
        }
        return new int[0];
    }
}
