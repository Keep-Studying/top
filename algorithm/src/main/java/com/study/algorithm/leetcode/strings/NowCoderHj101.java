/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * NowCoderHj101
 * 描述
 * 输入整型数组和排序标识，对其元素按照升序或降序进行排序
 *
 * 数据范围： 1 \le n \le 1000 \1≤n≤1000  ，元素大小满足 0 \le val \le 100000 \0≤val≤100000
 * 输入描述：
 * 第一行输入数组元素个数
 * 第二行输入待排序的数组，每个数用空格隔开
 * 第三行输入一个整数0或1。0代表升序排序，1代表降序排序
 *
 * 输出描述：
 * 输出排好序的数字
 * @author boyan
 * @version : NowCoderHj101.java, v 0.1 2023-01-05 14:39 boyan
 */
public class NowCoderHj101 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = in.nextInt();
            }
            int flag = in.nextInt();
            Arrays.sort(array);
            // 0即 升序
            if (flag == 0){
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i]+" ");
                }
            }else if (flag == 1){
                for (int i = array.length -1; i >= 0; i--) {
                    System.out.print(array[i]+" ");
                }
            }
        }
    }
}