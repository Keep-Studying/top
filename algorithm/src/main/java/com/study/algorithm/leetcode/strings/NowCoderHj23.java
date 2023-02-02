/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * NowCoderHj23
 * 描述
 * 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 *
 * 数据范围：输入的字符串长度满足 1 \le n \le 20 \1≤n≤20  ，保证输入的字符串中仅出现小写字母
 * 输入描述：
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 *
 * 输出描述：
 * 删除字符串中出现次数最少的字符后的字符串。
 * @author boyan
 * @version : NowCoderHj23.java, v 0.1 2023-01-05 14:08 boyan
 */
public class NowCoderHj23 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            char[] chars = s.toCharArray();
            //统计每个字母的数量
            HashMap<Character, Integer> map = new HashMap<>();
            for (char aChar : chars) {
                map.put(aChar, (map.getOrDefault(aChar, 0) + 1));
            }
            //找到数量最少的字符数量
            Collection<Integer> values = map.values();
            Integer min = Collections.min(values);

            //用空字符串替换该字母
            for (Character character : map.keySet()) {
                if (map.get(character).equals(min) ){
                    s = s.replaceAll(String.valueOf(character), "");
                }
            }
            System.out.println(s);
        }
    }
}