/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import org.junit.Test;

import java.util.Stack;

/**
 * LeetCode394
 * 字符串解码
 * @author boyan
 * @version : LeetCode394.java, v 0.1 2022-12-27 15:39 boyan
 */
public class LeetCode394 {

    @Test
    public void test001(){
        String s = "10[ab]6[c]d";
        String s1 = decodeString(s);
        System.out.println(s1);
    }

    public static String decodeString(String s){
        String result = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resultStack = new Stack<>();
        int index = 0;
        while (index  < s.length()){
            char cur = s.charAt(index);
            // 处理数字
            if(Character.isDigit(cur)){
                StringBuffer ret = new StringBuffer();
                while (Character.isDigit(s.charAt(index))){
                    ret.append(s.charAt(index++));
                }
                countStack.push(Integer.parseInt(ret.toString()));
            }else if(cur == '['){
                // 处理[
                resultStack.push(result);
                result = "";
                index++;
            }else if(cur == ']'){
                //处理']'，处理相匹配的'['之间的字符
                StringBuffer temp = new StringBuffer(resultStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(result);
                }
                result = temp.toString();
                index++;
            }else {
                //处理普通字符
                result += s.charAt(index++);
            }
        }
        return result;
    }
}