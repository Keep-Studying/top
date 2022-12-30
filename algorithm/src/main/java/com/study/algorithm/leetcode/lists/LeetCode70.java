/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode70
 * 爬楼梯问题
 *
 * 描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * 数据范围：1 \leq n \leq 401≤n≤40
 * 要求：时间复杂度：O(n)O(n) ，空间复杂度： O(1)O(1)
 *
 * @author boyan
 * @version : LeetCode70.java, v 0.1 2022-12-27 10:59 boyan
 */
public class LeetCode70 {

    private Map<Integer,Integer> storeMap = new HashMap<>();

    /**
     * f(1) = 1
     * f(2) = 2
     * f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int climbStairs(int n){
        if ( n == 1) {
            return 1;
        }
        if ( n == 2) {
            return 2;
        }
        if(storeMap.get(n) != null){
            return storeMap.get(n);
        }else {
            int result = climbStairs(n-1) + climbStairs(n-2);
            storeMap.put(n,result);
            return result;
        }
    }

    /**
     * 描述
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     *
     * 数据范围：1 \leq n \leq 401≤n≤40
     * 要求：时间复杂度：O(n) ，空间复杂度： O(1)
     * 用动态规划，优化掉递归栈空间。 方法二是从上往下递归的然后再从下往上回溯的，最后回溯的时候来合并子树从而求得答案。
     * 那么动态规划不同的是，不用递归的过程，直接从子树求得答案。过程是从下往上。
     * @param n
     * @return
     */
    public int jz69JumpFloor(int n){
        int a = 1, b =1, c = 1;
        for (int i = 2; i <= n; i++) {
            c = a+b;
            a = b;
            b = c;
        }
        return c;
    }

    @Test
    public void testJz69JumpFloor(){
        System.out.println(jz69JumpFloor(2));
        System.out.println(jz69JumpFloor(7));
    }
}