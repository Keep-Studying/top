/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

/**
 * LeetCode88
 * 合并两个有序数组
 * @author boyan
 * @version : LeetCode88.java, v 0.1 2022-12-27 11:33 boyan
 */
public class LeetCode88 {

    public void merge(int[] nums1,int m,int[] nums2,int n){
        int k = m + n;
        for (int index = k-1,nums1Index = m-1,nums2Index = n-1; index >= 0 ; index--) {
            if(nums1Index < 0){
                //nums1已经取完，完全取nums2的值即可
                nums1[index] = nums2[nums2Index--];
            }else if(nums2Index < 0){
                //nums2已经取完，完全取nums1的值即可
                break;
            }else if( nums1[nums1Index] > nums2[nums2Index]){
                //nums1的元素大于等于nums2，则取nums1的值
                nums1[index] = nums1[nums1Index--];
            }else {
                //nums2的元素大于等于nums1，则取nums2的值
                nums1[index] = nums2[nums2Index--];
            }
        }
    }
}