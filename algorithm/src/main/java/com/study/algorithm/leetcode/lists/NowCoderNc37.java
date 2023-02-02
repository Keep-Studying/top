/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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


    @Test
    public void test001(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(3, 6));

        ArrayList<Interval> merge = merge(intervals);
        System.out.println(JSON.toJSONString(merge));
    }

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


    @Test
    public void test002(){
        int[][] merge = merge(new int[][] {{1, 3}, {2, 6}, {8, 10},{15, 18}});
        System.out.println(JSON.toJSONString(merge));
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}