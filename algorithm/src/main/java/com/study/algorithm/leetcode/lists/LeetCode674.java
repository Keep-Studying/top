/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * LeetCode674
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-continuous-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCode674.java, v 0.1 2023-01-11 20:11 boyan
 */
public class LeetCode674 {

    @Test
    public void test001(){
        int[] nums = new int[]{1,3,5,4,7};
        int lengthOfLCIS = findLengthOfLCIS(nums);
        System.out.println(lengthOfLCIS);
    }

    public int findLengthOfLCIS(int[] nums) {
        int ans = 0 ;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] <= nums[i-1]){
                start = i;
            }
            ans = Math.max(ans,i - start + 1);
        }
        return ans;
    }

    public int findLengthOfLCIS1(int[] nums) {
        int ans = 0 ;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i>0 && nums[i] <= nums[i-1]){
                start = i;
            }
            ans = Math.max(ans,i-start+1);
        }
        return ans;
    }
}