/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode88
 * 合并两个有序数组
 * @author boyan
 * @version : LeetCode88.java, v 0.1 2022-12-27 11:33 boyan
 */
public class LeetCode88 {

    @Test
    public void test001() {
        int[] nums1 = new int[]{1,2,4,6};
        int[] nums2 = new int[]{3,5,7};
        int[] merge = merge(nums1, nums1.length, nums2, nums2.length);
        System.out.println(Arrays.toString(merge));
    }

    public int[] merge(int[] nums1,int m,int[] nums2,int n){
        int k = m + n;
        int[] result = new int[k];
        for (int index = k-1,nums1Index = m-1,nums2Index = n-1; index >= 0 ; index--) {
            if(nums1Index < 0){
                //nums1已经取完，完全取nums2的值即可
                result[index] = nums2[nums2Index--];
            }else if(nums2Index < 0){
                //nums2已经取完，完全取nums1的值即可
                result[index] = nums1[nums1Index--];
            }else if( nums1[nums1Index] > nums2[nums2Index]){
                //nums1的元素大于等于nums2，则取nums1的值
                result[index] = nums1[nums1Index--];
            }else {
                //nums2的元素大于等于nums1，则取nums2的值
                result[index] = nums2[nums2Index--];
            }
        }
        return result;
    }
}