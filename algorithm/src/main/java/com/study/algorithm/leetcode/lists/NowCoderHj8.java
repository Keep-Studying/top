/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * NowCoderHj8
 * 描述
 * 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
 *
 * 提示:
 * 0 <= index <= 11111111
 * 1 <= value <= 100000
 *
 * 输入描述：
 * 先输入键值对的个数n（1 <= n <= 500）
 * 接下来n行每行输入成对的index和value值，以空格隔开
 *
 * 输出描述：
 * 输出合并后的键值对（多行）
 *
 * @author boyan
 * @version : NowCoderHj8.java, v 0.1 2023-01-11 14:45 boyan
 */
public class NowCoderHj8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tableSize = scanner.nextInt();
        Map<Integer, Integer> table = new HashMap<>(tableSize);
        for (int i = 0; i < tableSize; i++) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();
            if (table.containsKey(key)){
                table.put(key,table.get(key) + value);
            }else {
                table.put(key,value);
            }
        }
        for(Integer key :table.keySet()){
            System.out.println(key + " "+ table.get(key));
        }
    }
}