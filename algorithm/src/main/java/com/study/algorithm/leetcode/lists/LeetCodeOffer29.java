/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCodeOffer29
 * 顺时针打印矩阵
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/shun-shi-zhen-da-yin-ju-zhen-by-leetcode-solution/
 * @author boyan
 * @version : LeetCodeOffer29.java, v 0.1 2022-12-28 10:25 boyan
 */
public class LeetCodeOffer29 {

    @Test public void test001() {
        //输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        //输出：[1,2,3,4,8,12,11,10,9,5,6,7]
        int[][] matrix = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[] ints = spiralOrder(matrix);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 按层模拟
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            // 上
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            // 右
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                // 下
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                // 左
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
