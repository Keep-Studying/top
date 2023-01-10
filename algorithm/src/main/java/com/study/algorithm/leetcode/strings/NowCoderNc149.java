/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import org.junit.Test;

/**
 * NowCoderNc149
 * 描述
 * 给你一个文本串 T ，一个非空模板串 S ，问 S 在 T 中出现了多少次
 *
 * 数据范围：1 \le len(S) \le 500000, 1 \le len(T) \le 10000001≤len(S)≤500000,1≤len(T)≤1000000
 * 要求：空间复杂度 O(len(S))O(len(S))，时间复杂度 O(len(S)+len(T))O(len(S)+len(T))
 * 示例1
 * 输入：
 * "ababab","abababab"
 * 返回值：
 * 2
 * @author boyan
 * @version : NowCoderNc149.java, v 0.1 2023-01-05 15:02 boyan
 */
public class NowCoderNc149 {

    @Test
    public void test001(){
        String s="ababab",t = "abababab";
        int kmp = kmp(s, t);
        System.out.println(kmp);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算模板串S在文本串T中出现了多少次
     * @param S string字符串 模板串
     * @param T string字符串 文本串
     * @return int整型
     */
    public int kmp(String S, String T) {
        // write code here
        int m = S.length(), n = T.length();
        // 特殊情况判断
        if (m > n || n == 0) {
            return 0;
        }
        //出现次数
        int cnt = 0;
        //分别遍历主串和模式串
        for (int i = 0, j = 0; i < n; i++) {
            //只要不相等，模式串回退到0位置
            while (j > 0 && T.charAt(i) != S.charAt(j)) {
                i = i - j + 1;
                j = 0;
            }
            if (T.charAt(i) == S.charAt(j)) {
                j++;
            }
            //如果j等于m，说明所有字符都匹配上了
            if (j == m) {
                //次数加一，同时i回退到上次匹配开头的下一位，j回到0
                cnt++;
                i = i - j + 2;
                j = 0;
            }
        }
        return cnt;
    }
}