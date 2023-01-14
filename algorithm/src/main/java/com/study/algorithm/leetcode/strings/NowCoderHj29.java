/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.Scanner;

/**
 * NowCoderHj29
 * 字符串加解密
 * 描述
 * 对输入的字符串进行加解密，并输出。
 *
 * 加密方法为：
 *
 * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
 *
 * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
 *
 * 其他字符不做变化。
 *
 * 解密方法为加密的逆过程。
 * 数据范围：输入的两个字符串长度满足 1 \le n \le 1000 \1≤n≤1000  ，保证输入的字符串都是只由大小写字母或者数字组成
 * 输入描述：
 * 第一行输入一串要加密的密码
 * 第二行输入一串加过密的密码
 *
 * 输出描述：
 * 第一行输出加密后的字符
 * 第二行输出解密后的字符
 *
 * 输入：
 * abcdefg
 * BCDEFGH
 * 输出：
 * BCDEFGH
 * abcdefg
 * @author boyan
 * @version : NowCoderHj29.java, v 0.1 2023-01-12 09:53 boyan
 */
public class NowCoderHj29 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            System.out.println(encode(in.nextLine()));
            System.out.println(decode(in.nextLine()));
        }
    }

    /**
     * 加密函数
     * @param code
     * @return
     */
    public static String encode(String code) {
        if (code == null){
            return null;
        }
        char[] chars = code.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] < 'z'){
                chars[i] =(char)(chars[i] -'a' + 'A'+1);
            }else if(chars[i] == 'z'){
                chars[i] = 'A';
            }else if (chars[i] >= 'A' && chars[i] < 'Z'){
                chars[i] =(char)(chars[i] -'A' + 'a'+1);
            }else if(chars[i] == 'Z'){
                chars[i] = 'a';
            }else if (chars[i] >= '0' && chars[i] < '9'){
                chars[i] =(char)(chars[i]+1);
            }else if(chars[i] == '9'){
                chars[i] = '0';
            }
        }
        return String.valueOf(chars);
    }

    /**
     * 解密函数
     * @param code
     * @return
     */
    public static String decode(String code) {
        if (code == null){
            return null;
        }
        char[] chars = code.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 'a' && chars[i] <= 'z'){
                chars[i] =(char)(chars[i] -'a' + 'A'-1);
            }else if(chars[i] == 'a'){
                chars[i] = 'Z';
            }else if (chars[i] > 'A' && chars[i] <= 'Z'){
                chars[i] =(char)(chars[i] -'A' + 'a'-1);
            }else if(chars[i] == 'A'){
                chars[i] = 'z';
            }else if (chars[i] > '0' && chars[i] <= '9'){
                chars[i] =(char)(chars[i]-1);
            }else if(chars[i] == '0'){
                chars[i] = '9';
            }
        }
        return String.valueOf(chars);
    }
}