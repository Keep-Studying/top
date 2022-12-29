/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode283
 * 移动零
 *
 * @author boyan
 * @version : LeetCode283.java, v 0.1 2022-12-27 11:52 boyan
 */
public class LeetCode283 {

    @Test
    public void test001(){
        int[] nums =new int[]{5,6,0,7,9};
        moveZeros(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeros(int[] nums) {
        if (nums == null) {
            return;
        }
        int j = 0;
        //第一次遍历的时候，j指针记录非0的个数，只有是非0的统统赋值给nums[j]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完成，则剩下的全部都是0
        //则第二次遍历把尾部的元素都赋值为0即可
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}