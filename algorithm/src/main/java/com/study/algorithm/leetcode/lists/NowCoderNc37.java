/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.ArrayList;
import java.util.Collections;
/**
 * NowCoderHj37
 *
 * 描述
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 *
 * 数据范围：区间组数 0 \le n \le 2 \times 10^50≤n≤2×10
 * 5
 *  ，区间内 的值都满足 0 \le val \le 2 \times 10^50≤val≤2×10
 * 5
 *
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)
 * 进阶：空间复杂度 O(val)O(val)，时间复杂度O(val)O(val)
 * @author boyan
 * @version : NowCoderHj37.java, v 0.1 2023-01-11 15:34 boyan
 */
public class NowCoderNc37 {

    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<>();
        if (intervals.size() == 0) {
            return result;
        }
        Collections.sort(intervals, (v1,v2) ->v1.start - v2.start);
        int index = -1;
        for(Interval interval :intervals){
            if(index == - 1 ||interval.start >result.get(index).end){
                // 区间 [a,b]  [c,d]   c 大于 b的话
                //不合并
                result.add(interval);
                index ++ ;
            }else{
                //如果 c 大于 b 那么就需要找一个最大的作为 右边的边界 因为数据start都从左到右排好序了
                result.get(index).end = Math.max(interval.end, result.get(index).end);
            }

        }
        return result;
    }

}