/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

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
}