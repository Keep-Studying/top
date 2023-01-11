/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * NowCoderHj41
 * 称砝码
 * https://www.nowcoder.com/practice/f9a4c19050fc477e9e27eb75f3bfd49c?tpId=37&tqId=21264&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D37%26type%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
 *
 * 描述
 * 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
 * 每种砝码对应的数量为 x1,x2,x3...xn 。现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
 *
 *
 * 注：
 *
 * 称重重量包括 0
 *
 * 数据范围：每组输入数据满足 1 \le n \le 10 \1≤n≤10  ， 1 \le m_i \le 2000 \1≤m
 * i
 * ​
 *  ≤2000  ， 1 \le x_i \le 10 \1≤x
 * i
 * ​
 *  ≤10
 * 输入描述：
 * 对于每组测试数据：
 * 第一行：n --- 砝码的种数(范围[1,10])
 * 第二行：m1 m2 m3 ... mn --- 每种砝码的重量(范围[1,2000])
 * 第三行：x1 x2 x3 .... xn --- 每种砝码对应的数量(范围[1,10])
 * 输出描述：
 * 利用给定的砝码可以称出的不同的重量数
 * @author boyan
 * @version : NowCoderHj41.java, v 0.1 2023-01-11 21:42 boyan
 */
public class NowCoderHj41 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            //存放所有可能的结果，不用担心重复问题
            HashSet<Integer> set = new HashSet<>();
            //初始化0
            set.add(0);
            //个数
            int n = in.nextInt();
            int[] w = new int[n];
            int[] nums = new int[n];
            for(int i=0;i<n;i++){
                //砝码的重量
                w[i] = in.nextInt();
            }
            for(int i=0;i<n;i++){
                //砝码个数
                nums[i] = in.nextInt();
            }
            //遍历砝码
            for(int i=0;i<n;i++){
                //取当前所有的结果
                ArrayList<Integer> list = new ArrayList<>(set);
                //遍历个数
                for(int j=1;j<=nums[i];j++){
                    for(int k=0;k<list.size();k++){
                        set.add(list.get(k) + w[i] * j);
                    }
                }
            }
            System.out.println(set.size());
        }
    }
}