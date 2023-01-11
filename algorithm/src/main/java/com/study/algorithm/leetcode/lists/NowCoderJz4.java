/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * NowCoderJz4
 * 描述
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 *
 * 给定 target = 3，返回 false。
 *
 * 数据范围：矩阵的长宽满足 0 \le n,m \le 5000≤n,m≤500 ， 矩阵中的值满足 0 \le val \le 10^90≤val≤10
 * 9
 *
 * 进阶：空间复杂度 O(1)O(1) ，时间复杂度 O(n+m)O(n+m)
 * @author boyan
 * @version : NowCoderJz4.java, v 0.1 2023-01-11 16:14 boyan
 */
public class NowCoderJz4 {


    @Test
    public void test001(){
        int[][] array = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        boolean b = find(7, array);
        System.out.println(b);
    }


    public boolean find(int target , int[][] array){
        //优先判断特殊
        if (array.length == 0){
            return false;
        }
        // rows
        int n = array.length;
        // columns 为0，则直接返回false
        if (array[0].length == 0){
            return false;
        }
        int m = array[0].length;
        for (int i = n-1,j = 0; i >= 0 && j< m  ; ) {
            if (array[i][j] > target){
                //元素较大，往上走
                i--;
            }else if (array[i][j] < target){
                //元素较小，往右走
                j++;
            }else {
                return true;
            }
        }
        return false;
    }
}