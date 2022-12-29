/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode448
 * 找到数组中消失的数字
 * 给定一个数组，其中nums[i]在区间[1,n]内，请找出在[1,n]范围内
 * 没有出现在nums中的数字，并以数组的形式返回结果
 * ps：不能使用额外的存储空间
 * @author boyan
 * @version : LeetCode448.java, v 0.1 2022-12-27 12:06 boyan
 */
public class LeetCode448 {

    @Test
    public void test001(){
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        List<Integer> numbers = findNumbers(nums);
        System.out.println(numbers.toString());
    }

    public List<Integer> findNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 对n取模还原它本来的值
            int x = (nums[i] - 1) % n;
            nums[x] += n;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(nums[i] <= n ){
                result.add(i+1);
            }
        }
        return result;
    }
}