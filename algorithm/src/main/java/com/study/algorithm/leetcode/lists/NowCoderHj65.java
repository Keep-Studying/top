/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.Scanner;

/**
 * NowCoderHj65
 * 描述
 * 查找两个字符串a,b中的最长公共子串。若有多个，输出在较短串中最先出现的那个。
 * 注：子串的定义：将一个字符串删去前缀和后缀（也可以不删）形成的字符串。请和“子序列”的概念分开！
 *
 * 数据范围：字符串长度1\le length \le300 \1≤length≤300
 * 进阶：时间复杂度：O(n^3)\O(n
 * 3
 *  ) ，空间复杂度：O(n)\O(n)
 * 输入描述：
 * 输入两个字符串
 *
 * 输出描述：
 * 返回重复出现的字符
 * @author boyan
 * @version : NowCoderHj65.java, v 0.1 2023-01-11 16:03 boyan
 */
public class NowCoderHj65 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        if (s1.length() > s2.length()) {
            // 确保s1比较短
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        // 存储最长子串
        String result = "";
        // 左指针
        int left = 0;
        // 右指针，从第一个字符开始移动
        int right = left + 1;
        while (right <= s1.length()) {
            String tmp = s1.substring(left, right);
            if (s2.contains(tmp)) {
                if (result.length() < tmp.length()) {
                    result = tmp;
                }
                // 是子串的情况下，只移动右指针
                right++;
            } else {
                // 左指针移动一位
                left++;
                right = left + 1;
            }
        }
        System.out.println(result);
    }

    /**
     * 求最长公共子串，str1为较短串
     * @param str1
     * @param str2
     * @return
     */
    public static String longString(String str1,String str2){
        //如果str1为str2子串，直接返回
        if (str2.contains(str1)) {
            return str1;
        }
        //记录最长公共子串的长度
        int len = str1.length() -1;
        for (int i = 0; i < str1.length(); i++) {
            //说明没有公共子串
            if (len == 0){
                break;
            }
            //在str1中截取长度len的子串，如果是str2的子串则返回
            if(i + len <= str1.length()){
                String s = str1.substring(i,i+len);
                if (str2.contains(s)){
                    return s;
                }else {
                    len--;
                    i = -1;
                }
            }
        }
        return null;
    }
}