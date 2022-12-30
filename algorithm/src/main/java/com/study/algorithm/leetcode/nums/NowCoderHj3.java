/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import java.util.Arrays;
import java.util.Scanner;

/**
 * NowCoderHj3
 * 明明的随机数
 * 描述
 * 明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 *
 * 数据范围： 1 \le n \le 1000 \1≤n≤1000  ，输入的数字大小满足 1 \le val \le 500 \1≤val≤500
 * 输入描述：
 * 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
 * 输出描述：
 * 输出多行，表示输入数据处理后的结果
 *
 * 输入：
 * 3
 * 2
 * 2
 * 1
 * 输出：
 * 1
 * 2
 * @author boyan
 * @version : NowCoderHj3.java, v 0.1 2022-12-30 22:10 boyan
 */
public class NowCoderHj3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            int[] intArray = new int[n];
            for(int i = 0; i < n;i++){
                intArray[i] = in.nextInt();
            }
            Arrays.sort(intArray);
            for(int j = 0;j< intArray.length;j++){
                if (j == 0 || intArray[j] != intArray[j-1]){
                    System.out.println(intArray[j]);
                }
            }
        }
    }
}