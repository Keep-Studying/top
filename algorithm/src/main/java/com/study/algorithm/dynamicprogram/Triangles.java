/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.dynamicprogram;

import java.util.Arrays;

/**
 * Triangles
 * 三角形最大周长
 * 给定由一些正数（代表长度）组成的数组，返回其中由3个长度组成的、面积不为零的三角形的最大周长
 * 如果不能形成任何面积不为零的三角形，则返回零
 * @author boyan
 * @version : Triangles.java, v 0.1 2022-12-07 17:16 boyan
 */
public class Triangles {

    public static void main(String[] args) {
        System.out.println(largestPerimeter(new int[]{3,6,2,3}));
    }

    public static int largestPerimeter(int[] nums){
        // 首先对数组进行排序
        Arrays.sort(nums);
        for (int i = nums.length - 1;i >= 2;i--){
            //形成三角形的条件是两条边之和大于第三边，且第三边的长度比其他两个边要大
            if (nums[i-1] + nums[i-2] > nums[i]){
                return nums[i-1] + nums[i-2] + nums[i];
            }
        }
        return 0;
    }
}