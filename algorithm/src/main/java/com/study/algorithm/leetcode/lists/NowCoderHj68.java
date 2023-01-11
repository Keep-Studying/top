/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * NowCoderHj68
 * 描述
 * 给定一些同学的信息（名字，成绩）序列，请你将他们的信息按照成绩从高到低或从低到高的排列,相同成绩
 *
 * 都按先录入排列在前的规则处理。
 *
 * 例示：
 * jack      70
 * peter     96
 * Tom       70
 * smith     67
 *
 * 从高到低  成绩
 * peter     96
 * jack      70
 * Tom       70
 * smith     67
 *
 * 从低到高
 *
 * smith     67
 *
 * jack      70
 *
 * Tom       70
 * peter     96
 *
 * 注：0代表从高到低，1代表从低到高
 *
 * 数据范围：人数：1\le n \le 200\1≤n≤200
 * 进阶：时间复杂度：O(nlogn)\O(nlogn) ，空间复杂度：O(n)\O(n)
 * 输入描述：
 * 第一行输入要排序的人的个数n，第二行输入一个整数表示排序的方式，之后n行分别输入他们的名字和成绩，以一个空格隔开
 * 输出描述：
 * 按照指定方式输出名字和成绩，名字和成绩之间以一个空格隔开
 * @author boyan
 * @version : NowCoderHj68.java, v 0.1 2023-01-11 15:54 boyan
 */
public class NowCoderHj68 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer,String> map = new HashMap<>();
        while (scanner.hasNextLine()){
            int n = Integer.parseInt(scanner.nextLine());
            int flag = Integer.parseInt(scanner.nextLine());
            // 姓名编号，成绩
            int[][] score = new int[n][2];
            for (int i = 0; i < n; i++) {
                String[] nameAndScore = scanner.nextLine().split("\\s+");
                score[i][0] = i;
                score[i][1] = Integer.parseInt(nameAndScore[1]);
                map.put(i,nameAndScore[0]);
            }
            Arrays.sort(score,(o1,o2) ->{
                if(flag==0){
                    return o2[1] - o1[1];//按第二列降序排列,如果相等的话，返回0，顺序不变
                }else{
                    return o1[1] - o2[1];//按第二列升序
                }
            });
            for(int i=0;i<n;i++){
                System.out.println(map.get(score[i][0]) + " " + score[i][1]);
            }
        }
        scanner.close();
    }
}