/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode70
 * 爬楼梯问题
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

}