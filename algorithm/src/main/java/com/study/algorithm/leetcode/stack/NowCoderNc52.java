/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * NowCoderNc52
 * 描述
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 *
 * 数据范围：字符串长度 0\le n \le 100000≤n≤10000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * @author boyan
 * @version : NowCoderNc52.java, v 0.1 2023-01-11 16:24 boyan
 */
public class NowCoderNc52 {

    @Test
    public void test001(){
        String str = "[]{}";
        boolean valid = isValid(str);
        System.out.println(valid);
        System.out.println(isValid1(str));
    }

    /**
     *
     * @param str string字符串
     * @return bool布尔型
     */
    public boolean isValid (String str) {
        Stack<Character> stack = new Stack<>();
        char[] chars = str.toCharArray();
        for (Character ch:chars) {
            switch (ch){
                case '{':
                case '(':
                case '[':
                    //O(1)
                    stack.push(ch);
                    break;
                case '}':
                    if(stack.empty() || stack.peek() != '{'){
                        return false;
                    }
                    stack.pop();
                    break;
                case ')':
                    if(stack.empty() || stack.peek() != '('){
                        return false;
                    }
                    stack.pop();
                    break;
                case ']':
                    if(stack.empty() || stack.peek() != '['){
                        return false;
                    }
                    stack.pop();
                    break;
                default:break;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid1(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch:str.toCharArray()) {
            if (ch == '{'){
                stack.push('}');
            }else if(ch == '('){
                stack.push(')');
            }else if(ch == '['){
                stack.push(']');
            }else if(stack.isEmpty() || stack.pop() != ch){
                return false;
            }
        }
        return stack.isEmpty();
    }
}