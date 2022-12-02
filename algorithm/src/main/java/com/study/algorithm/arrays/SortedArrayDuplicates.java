/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.arrays;

import org.junit.Test;

/**
 * SortedArrayDuplicates
 * 一个有序数组中，原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度
 * 不要使用额外的数组空间，必须在原地修改输入数组并且在使用O(1)额外空间的条件下完成
 *
 * 双指针算法：
 * 数组完成排序后，我们可以放置两个指针i合j，其中i是慢指针，j是快指针。只要nums[i]==nums[j]，
 * 我们就增加j以跳过重复项
 * 当遇到nums[i] !=nums[j]时，跳过重复项的运行已经结束，必须把nums[j]的值复制到nums[i+1],
 * 然后递增i，接着再重复相同的过程，直到j到达数组的末尾为止
 * @author boyan
 * @version : SortedArrayDuplicates.java, v 0.1 2022-12-02 17:34 boyan
 */
public class SortedArrayDuplicates {

    @Test public void test001() {
        int duplicates = removeDuplicates(new int[] {0, 1, 2, 2, 3, 3, 4});
        System.out.println(String.format("duplicates is %s ", duplicates));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //下标从0开始
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                // 如果元素不相等，则需要交换元素的值
                i++;
                nums[i] = nums[j];
            }
        }
        //数量需要将坐标为0的加上去
        return i + 1;
    }
}
