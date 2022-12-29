/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

/**
 * LeetCode415
 * 字符串相加
 * @author boyan
 * @version : LeetCode415.java, v 0.1 2022-12-27 18:29 boyan
 */
public class LeetCode415 {

    @Test public void test001() {
        String str1 = "11";
        String str2 = "123";
        String s = addStrings(str1, str2);
        System.out.println(s);
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        // 记录进位的变量
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }
}