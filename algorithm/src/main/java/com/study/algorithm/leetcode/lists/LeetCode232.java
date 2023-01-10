/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 * LeetCode232
 * 用栈实现队列
 * @author boyan
 * @version : LeetCode232.java, v 0.1 2022-12-27 15:34 boyan
 */
public class LeetCode232 {

    private static Stack<Integer> inStack ;
    private static Stack<Integer> outStack ;

    public LeetCode232(){
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x){
        inStack.push(x);
    }

    public int pop(){
        if(outStack.isEmpty()){
            in2out();
        }
        return outStack.pop();
    }

    public int peek(){
        if(outStack.isEmpty()){
            in2out();
        }
        return outStack.peek();
    }

    public boolean isEmpty(){
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out(){
        while (!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }

    @Test public void test001() {
        int[] ints = {5, 10, -5};
        int[] compute = compute(ints);
        System.out.println(JSON.toJSONString(compute));
    }

    public int[] compute(int[] nums) {
        if (nums == null) {
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            Integer peek = stack.peek();
            if (peek != null) {
                if (peek > 0 && nums[i] > 0) {
                    stack.push(nums[i]);
                } else if (peek < 0 && nums[i] < 0) {
                    stack.push(nums[i]);
                } else {
                    if (nums[i] > 0) {
                        int temp = peek * -1;
                        if (nums[i] > temp) {
                            stack.pop();
                            stack.push(nums[i]);
                        }
                    } else {
                        int temp = nums[i] * -1;
                        if (peek < temp) {
                            stack.pop();
                            stack.push(nums[i]);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> integers = new ArrayList<>();
        while (!stack.isEmpty()) {
            integers.add(stack.pop());
        }

        int size = integers.size();
        int[] result = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            result[size - 1 - i] = integers.get(i);
        }
        return result;
    }
}