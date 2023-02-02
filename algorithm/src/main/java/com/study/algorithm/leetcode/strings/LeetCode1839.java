/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import org.junit.Test;

/**
 * LeetCode1839
 * https://leetcode.cn/problems/longest-substring-of-all-vowels-in-order/
 *
 * 一个字符串满足如下条件时，我们称它是 美丽的 ：
 *
 * 所有 5 个英文元音字母（'a' ，'e' ，'i' ，'o' ，'u'）都必须 至少 出现一次。
 * 这些元音字母的顺序都必须按照 字典序 升序排布（也就是说所有的 'a' 都在 'e' 前面，所有的 'e' 都在 'i' 前面，以此类推）
 * 比方说，字符串 "aeiou" 和 "aaaaaaeiiiioou" 都是 美丽的 ，但是 "uaeio" ，"aeoiu" 和 "aaaeeeooo" 不是美丽的 。
 *
 * 给你一个只包含英文元音字母的字符串 word ，请你返回 word 中 最长美丽子字符串的长度 。如果不存在这样的子字符串，请返回 0 。
 *
 * 子字符串 是字符串中一个连续的字符序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-of-all-vowels-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author boyan
 * @version : LeetCode1839.java, v 0.1 2023-01-05 14:45 boyan
 */
public class LeetCode1839 {

    @Test
    public void test001(){
        String word = "aeiaaioaaaaeiiiiouuuooaauuaeiu";
        int length = longestBeautifulSubstring(word);
        System.out.println(length);
        System.out.println(longestBeautifulSubstring1(word));
    }

    public int longestBeautifulSubstring(String word) {
        int ans = 0, type = 1, len = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) >= word.charAt(i - 1)) {
                //更新当前字符串长度
                len++;
            }
            if (word.charAt(i) > word.charAt(i - 1)) {
                //更新当前字符种类
                type++;
            }
            if (word.charAt(i) < word.charAt(i - 1)) {
                //当前字符串不美丽，从当前字符重新开始
                type = 1;
                len = 1;
            }
            if (type == 5) {
                //更新最大字符串
                ans = Math.max(ans, len);
            }
        }
        return ans;
    }

    public int longestBeautifulSubstring1(String word) {
        int ans = 0, type = 1, len = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) >= word.charAt(i-1)){
                len++;
            }
            if (word.charAt(i) > word.charAt(i-1)){
                type++;
            }
            if (word.charAt(i) < word.charAt(i-1)){
                len = 1;
                type = 1;
            }
            if(type == 5){
                ans = Math.max(ans,len);
            }
        }
        return ans;
    }
}