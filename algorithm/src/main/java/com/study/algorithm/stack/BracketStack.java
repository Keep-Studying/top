/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.stack;

import java.util.Scanner;

/**
 * 括号匹配
 * 如何设计一个括号匹配的功能？比如给你一串括号让你判断是否符合我们的括号原则，如下所示：
 * [(){()}]符合，{}[]()[{()}]符合，{}[]{]不符合
 *
 * @author study
 * @version : BracketStack.java, v 0.1 2020年06月28日 0:19 study Exp $
 */
public class BracketStack {
    public static boolean isOk(String str){
        MyStack<Character> brackets = new ArrayStack<>(20);
        char[] chars = str.toCharArray();
        Character top;
        for (Character ch:chars
             ) {
            switch (ch){
                case '{':
                case '(':
                case '[':
                    brackets.push(ch);//O(1)
                    break;
                case '}':
                    top = brackets.pop();//O(1)
                    if(top == null) return false;
                    if(top == '{'){
                        break;
                    }else {
                        return false;
                    }
                case ')':
                    top = brackets.pop();//O(1)
                    if(top == null) return false;
                    if(top == '('){
                        break;
                    }else {
                        return false;
                    }
                case ']':
                    top = brackets.pop();//O(1)
                    if(top == null) return false;
                    if(top == '['){
                        break;
                    }else {
                        return false;
                    }
                default:break;
            }
        }
        return brackets.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            System.out.println("str的匹配结果为:"+isOk(str));
        }
    }
}