/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import org.junit.Test;

/**
 * NowCoderNc28
 *
 * 最小覆盖子串
 * 描述
 *
 * 给出两个字符串 s 和 t，要求在 s 中找出最短的包含 t 中所有字符的连续子串。
 *
 * 数据范围：0 \le |S|,|T| \le100000≤∣S∣,∣T∣≤10000，保证s和t字符串中仅包含大小写英文字母
 * 要求：进阶：空间复杂度 O(n)O(n) ， 时间复杂度 O(n)O(n)
 * 例如：
 * S ="XDOYEZODEYXNZ"S="XDOYEZODEYXNZ"
 * T ="XYZ"T="XYZ"
 * 找出的最短子串为"YXNZ""YXNZ".
 *
 * 注意：
 * 如果 s 中没有包含 t 中所有字符的子串，返回空字符串 “”；
 * 满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。
 * @author boyan
 * @version : NowCoderNc28.java, v 0.1 2023-01-11 20:43 boyan
 */
public class NowCoderNc28 {

    @Test
    public void test001(){
        String s = "XDOYEZODEYXNZ";
        String t = "XYZ";
        String s1 = minWindow(s, t);
        System.out.println(s1);
    }

    /**
     * 检查是否有小于0的
     * @param hash
     * @return
     */
    public boolean check(int[] hash){
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] < 0){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param S string字符串
     * @param T string字符串
     * @return string字符串
     */
    public String minWindow(String S, String T) {
        int cnt = S.length() + 1;
        // 记录目标字符串T的字符个数
        int[] hash = new int[128];
        for (int i = 0; i < T.length(); i++) {
            //初始化哈希表都为负数，找的时候再加为正
            hash[T.charAt(i)] -= 1;
        }
        int slow = 0, fast = 0;
        //记录左右区间
        int left = -1, right = -1;
        for (;fast < S.length();fast++){
            char c = S.charAt(fast);
            // 目标字符串+1
            hash[c]++;
            //没有小于0的说明都覆盖了，缩小窗口
            while (check(hash)){
                if(cnt > fast - slow +1){
                    cnt = fast -slow+1;
                    left = slow;
                    right = fast;
                }
                c = S.charAt(slow);
                // 缩小窗口的时候减少1
                hash[c]--;
                // 窗口缩小
                slow++;
            }
        }
        if(left == -1){
            return "";
        }
        return S.substring(left, right + 1);
    }
}