/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * NowCoderTest01
 *
 * @author boyan
 * @version : NowCoderTest01.java, v 0.1 2023-01-14 23:19 boyan
 */
public class NowCoderTest01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] split = str.split("\\s+");
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            priorityQueue.offer(s);
        }
        while (!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.poll()+" ");
        }
        System.out.println("  ");
        sc.close();
    }
}