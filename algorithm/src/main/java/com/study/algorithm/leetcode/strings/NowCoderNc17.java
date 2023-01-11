/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import org.junit.Test;

/**
 * NowCoderNc17
 * 描述
 * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
 *
 *
 * 数据范围： 1 \le n \le 10001≤n≤1000
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n^2)O(n
 * 2
 *  )
 * 进阶:  空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * @author boyan
 * @version : NowCoderNc17.java, v 0.1 2023-01-11 20:32 boyan
 */
public class NowCoderNc17 {

    @Test
    public void test001(){
        String str = "ababc";
        int longestPalindrome = getLongestPalindrome(str);
        System.out.println(longestPalindrome);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return int整型
     */
    public int fun(String s, int begin, int end){
        //每个中心点开始扩展
        while(begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)){
            begin--;
            end++;
        }
        //返回长度
        return end - begin - 1;
    }
    public int getLongestPalindrome (String A) {
        int maxlen = 1;
        //以每个点为中心
        for(int i = 0; i < A.length() - 1; i++){
            //分奇数长度和偶数长度向两边扩展
            maxlen = Math.max(maxlen, Math.max(fun(A, i, i), fun(A, i, i + 1)));
        }
        return maxlen;
    }
}