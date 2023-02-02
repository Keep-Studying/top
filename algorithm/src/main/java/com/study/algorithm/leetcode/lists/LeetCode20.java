/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.Stack;

/**
 * LeetCode20
 *
 * @author boyan
 * @version : LeetCode20.java, v 0.1 2022-12-27 17:58 boyan
 */
public class LeetCode20 {

    @Test
    public void test001(){
        String s = "[({})]";
        boolean valid = isValid(s);
        System.out.println(valid);
        System.out.println(isValid1(s));
    }

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '('){
                stack.push(')');
            }else if(ch == '{'){
                stack.push('}');
            }else if(ch == '['){
                stack.push(']');
            }else if(stack.isEmpty() || stack.pop() != ch){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid1(String s){
        Stack<Character> stack = new Stack<>();
        for (char ch :s.toCharArray()) {
            if (ch == '('){
                stack.push(')');
            }else if (ch == '{'){
                stack.push('}');
            }else if (ch == '['){
                stack.push(']');
            }else if (stack.isEmpty() || stack.pop() != ch){
                return false;
            }
        }
        return stack.isEmpty();
    }
}