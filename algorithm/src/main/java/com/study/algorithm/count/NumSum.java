/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.count;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

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

    /**
     * 给定一定面额的钱，面额为1，5，10，20，每种钞票的数量是无限的，请计算出凑出指定金额数字如50的最少钞票数量
     *
     * 假如将10变为11，用这种算法有什么缺点
     *
     * 贪心算法来计算，最大面额依次降低，看哪种方式得到的数量最少
     * 贪心算法得到的解，不一定的最优解
     */
    @Test public void test001() {
        int nums[] = new int[] {1, 5, 10, 20};
        int target = 50;
        System.out.println(String.format("target is %s , nums is %s , min count is %s", target, JSON.toJSONString(nums),
            compute(nums, target)));
    }

    public static int compute(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int computeNum = computeNum(i, nums, target);
            System.out.println(
                String.format("target is %s , index is %s , num[%s] is %s , computeNum is %s", target, i, i, nums[i],
                    computeNum));
            min = Math.min(min, computeNum);
        }
        return min;
    }

    public static int computeNum(int index, int[] nums, int targetNum) {
        System.out.println("====================start======================");
        int count = 0;
        int target = targetNum;
        for (int i = index; i >= 0; i--) {
            if (target == 0) {
                break;
            }
            while ((target - nums[i]) >= 0 || (target - nums[i]) >= nums[i]) {
                count++;
                System.out.println(
                    String.format("index is %s , target is %s , nums[%s] is %s , count is %s", index, target, i,
                        nums[i], count));
                target = target - nums[i];
            }
        }
        System.out.println("=====================end=====================");
        return count;
    }
}
