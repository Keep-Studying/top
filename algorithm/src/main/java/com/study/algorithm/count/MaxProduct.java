/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */

package com.study.algorithm.count;

import java.util.Arrays;

/**
 * MaxProduct
 * 给定一个数组，求数组中3个数字的最大乘积，假设乘积不会越界
 *
 * @author boyan
 * @version : MaxProduct.java, v 0.1 2022-12-06 20:10 boyan
 */
public class MaxProduct {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 3, 4, 5, 6};
        System.out.println(String.format("nums MaxProduct by sort is %s",sort(nums)));
        System.out.println(String.format("nums MaxProduct by maxMin is %s",maxMin(nums)));
    }

    public static int sort(int[] nums){
        Arrays.sort(nums);
        int length = nums.length;
        return Math.max(nums[0] * nums[1] * nums[length-1] , nums[length-1] * nums[length-2] * nums[length-3]);
    }

    public static int maxMin(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int x : nums) {
            if (x < min1){
                min2 = min1;
                min1 = x;
            }else if (x < min2){
                min2 = x;
            }

            if (x > max1){
                max3 = max2;
                max2 = max1;
                max1 = x;
            }else if (x > max2){
                max3 = max2;
                max2 = x;
            }else if (x > 3){
                max3 = x;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

}
