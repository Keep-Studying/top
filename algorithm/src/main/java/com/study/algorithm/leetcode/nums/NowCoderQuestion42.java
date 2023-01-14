/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * NowCoderQuestion42
 * 火锅问题
 *
 * 入职后，导师会请你吃饭，你选择了火锅。
 * 火锅里会在不同时间下很多菜。
 * 不同食材要煮不同的时间，才能变得刚好合适。你希望吃到最多的刚好合适的菜，但你的手速不够快，
 * 用m代表手速，每次下手捞菜后至少要过m秒才能在捞（每次只能捞一个）。
 * 那么用最合理的策略，最多能吃到多少刚好合适的菜？
 * 输入描述：
 * 第一行两个整数n，m，其中n代表往锅里下的菜的个数，m代表手速。
 * 接下来有n行，每行有两个数x，y代表第x秒下的菜过y秒才能变得刚好合适。
 * （1 < n, m < 1000）
 * （1 < x, y < 1000）
 * 输出描述：
 * 输出一个整数代表用最合理的策略，最多能吃到刚好合适的菜的数量
 * @author boyan
 * @version : NowCoderQuestion42.java, v 0.1 2023-01-12 11:46 boyan
 */
public class NowCoderQuestion42 {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2}, {2, 1}};
        answer(arr, 1);
    }

    /**
     * 分析：准备好时，如果当前的菜已经熟了，则等待下一个，如果刚好熟了或者还没熟，则等待现在的菜
     * @param arr 所有的菜煮熟的时间
     * @param m 手速
     */
    public static void answer(int[][] arr, int m) {
        //定义一个数组，
        int[] timeAndReady = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            timeAndReady[i] = arr[i][0]+arr[i][1];
        }
        Arrays.sort(timeAndReady);
        int count = 0 ;
        int timeCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (m < timeAndReady[i] - timeCount){
                count++;
                timeCount = timeAndReady[i];
            }
        }
        System.out.println(JSON.toJSONString(timeAndReady));
        System.out.println(count);
    }
}