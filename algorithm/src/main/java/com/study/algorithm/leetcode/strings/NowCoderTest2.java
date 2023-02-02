/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * NowCoderTest2
 *
 * @author boyan
 * @version : NowCoderTest2.java, v 0.1 2023-01-14 17:00 boyan
 */
public class NowCoderTest2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int m = Integer.parseInt(sc.nextLine());
            List<List<Integer>> nums = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                String nextLine = sc.nextLine();
                String[] split = nextLine.split(",");
                Set<Integer> integers = new TreeSet<>();
                for (int j = 0; j < split.length; j++) {
                    int parseInt = Integer.parseInt(split[j]);
                    integers.add(parseInt);
                }
                List<Integer> list = new ArrayList<>(integers);
                if (list == null){
                    continue;
                }
                nums.add(list);
            }
            // 处理结果
            List<List<Integer>> result = new ArrayList<>();
            HashSet<Integer> hashSet = new HashSet<>();
            for (int i = 0; i < nums.size(); i++) {
                List<Integer> iList = nums.get(i);
                if (hashSet.contains(i)) {
                    continue;
                }
                if (iList.size() <= 1) {
                    result.add(iList);
                    hashSet.add(i);
                    continue;
                }
                for (int j = i + 1; j < nums.size(); j++) {
                    if (hashSet.contains(j)) {
                        continue;
                    }
                    List<Integer> jList = nums.get(j);
                    int count = 0;
                    boolean merge = false;
                    for (int k = 0; k < iList.size(); k++) {
                        if (jList.contains(iList.get(k))) {
                            count++;
                        }
                        if (count >= 2) {
                            merge = true;
                            break;
                        }
                    }
                    if (merge) {
                        hashSet.add(j);
                        iList.addAll(jList);
                        List<Integer> collect = iList.stream().distinct().sorted().collect(Collectors.toList());
                        iList = collect;
                    }
                }
                result.add(iList);
            }
            System.out.print("[");
            for (int i = 0; i < result.size(); i++) {
                System.out.print("[");
                List<Integer> integers = result.get(i);
                for (int j = 0; j < integers.size(); j++) {
                    if (j == integers.size() -1){
                        System.out.print(integers.get(j));
                    }else {
                        System.out.print(integers.get(j)+",");
                    }
                }
                System.out.print("]");
            }
            System.out.print("]");
            System.out.println();
        }
        sc.close();
    }
}