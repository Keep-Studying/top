/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.nums;

import java.util.Scanner;

/**
 * NowCoderHj5
 * HJ5 进制转换
 * 描述
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * 数据范围：保证结果在 1 \le n \le 2^{31}-1 \1≤n≤2
 * 31
 *  −1
 * 输入描述：
 * 输入一个十六进制的数值字符串。
 *
 * 输出描述：
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 *
 * 输入：
 * 0xAA
 * 输出：
 * 170
 * @author boyan
 * @version : NowCoderHj5.java, v 0.1 2022-12-30 15:41 boyan
 */
public class NowCoderHj5 {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            //Integer.parseInt()把整形对象Integer转换成基本数据类型int（整数）
            System.out.println(Integer.parseInt(s.substring(2, s.length()), 16));
        }
    }
}