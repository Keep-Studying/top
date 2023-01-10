/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.Scanner;

/**
 * NowCoderHj33
 * 描述
 * 原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成
 * 一个长整数。
 * 举例：一个ip地址为10.0.3.193
 * 每段数字             相对应的二进制数
 * 10                   00001010
 * 0                    00000000
 * 3                    00000011
 * 193                  11000001
 *
 * 组合起来即为：00001010 00000000 00000011 11000001,转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了。
 *
 * 数据范围：保证输入的是合法的 IP 序列
 *
 * 输入描述：
 * 输入
 * 1 输入IP地址
 * 2 输入10进制型的IP地址
 *
 * 输出描述：
 * 输出
 * 1 输出转换成10进制的IP地址
 * 2 输出转换后的IP地址
 *
 * 输入：
 * 10.0.3.193
 * 167969729
 * 输出：
 * 167773121
 * 10.3.3.193
 * @author boyan
 * @version : NowCoderHj33.java, v 0.1 2023-01-05 14:21 boyan
 */
public class NowCoderHj33 {

    public static String convert(String str) {
        int n = 4;
        // ipv4 -> int
        if (str.contains(".")) {
            String[] split = str.split("\\.");
            long result = 0;
            for (int i = 0; i < n; i++) {
                result = result * 256 + Integer.parseInt(split[i]);
            }
            return "" + result;
        } else {
            long ipv4 = Long.parseLong(str);
            String result = "";
            for (int i = 0; i < n; i++) {
                result = ipv4 % 256 + "." + result;
                ipv4 /= 256;
            }
            return result.substring(0, result.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String str = in.next();
            String convert = convert(str);
            System.out.println(convert);
        }
    }
}