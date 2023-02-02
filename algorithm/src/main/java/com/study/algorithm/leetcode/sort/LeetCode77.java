/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.sort;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode77
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 * https://leetcode.cn/problems/combinations/
 *
 * @author boyan
 * @version : LeetCode77.java, v 0.1 2023-01-11 16:54 boyan
 */
public class LeetCode77 {


    @Test
    public void test001(){
        List<List<Integer>> lists = combine(4, 2);
        System.out.println(JSON.toJSONString(lists));
        System.out.println(JSON.toJSONString(combine1(4, 2)));
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int index, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 只有这里 i <= n - (k - path.size()) + 1 与参考代码 1 不同
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            dfs(n, k, i + 1, path, res);
            path.removeLast();
        }
    }

    public List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n <k){
            return res;
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        dfs1(n,k,1,deque,res);
        return res;
    }

    private void dfs1(int n, int k, int index, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i <= n -( k- path.size())+1; i++) {
            path.add(i);
            dfs1(n,k,i+1,path,res);
            path.removeLast();
        }
    }


}