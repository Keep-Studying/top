/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.Scanner;

/**
 * NowCoderHj46
 * 描述
 * 输入一个字符串和一个整数 k ，截取字符串的前k个字符并输出
 *
 * 数据范围：字符串长度满足 1 \le n \le 1000 \1≤n≤1000  ， 1 \le k \le n \1≤k≤n
 * 输入描述：
 * 1.输入待截取的字符串
 *
 * 2.输入一个正整数k，代表截取的长度
 *
 * 输出描述：
 * 截取后的字符串
 * 输入：
 * abABCcDEF
 * 6
 * 输出：
 * abABCc
 * @author boyan
 * @version : NowCoderHj46.java, v 0.1 2023-01-05 15:00 boyan
 */
public class NowCoderHj46 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            // 输入字符串和k
            String str = sc.next();
            int k = sc.nextInt();
            // 直接使用String的substring方法输出结果
            System.out.println(str.substring(0,k));
        }
    }
}