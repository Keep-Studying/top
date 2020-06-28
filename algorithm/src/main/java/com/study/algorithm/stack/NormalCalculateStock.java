/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.stack;

import java.util.Scanner;

/**
 * 简单四则运算（用栈实现，目前只能计算0~9以内的数字加减乘除四则运算）
 *
 * 比如用栈实现一个简单的四则运算：3+5*2+8-9/3，用栈来实现这个算术表达式
 * 我们从头开始遍历这个算术表达式：
 * 1. 遇到是数字，我们就直接入栈到数字栈里面去
 * 2. 遇到是符号，就把符号栈的栈顶元素拿出来作比较，如果说它比栈顶元素符号的优先级低或者相同，
 *    就从符号栈里面取栈顶进行计算（从数字栈里面取栈顶的2个元素），计算完的结果还是要放到数字栈中
 * @author study
 * @version : NormalCalculateStock.java, v 0.1 2020年06月28日 23:25 study Exp $
 */
public class NormalCalculateStock {
    public static Integer calculate(String str){
        //数字栈
        MyStack<Integer> digitBrackets = new ArrayStack<>(20);
        //符号栈
        MyStack<Character> operatorBrackets = new ArrayStack<>(20);
        char[] chars = str.toCharArray();

        //入栈
        for (Character ch:chars) {
            if(isDigit(ch)){
                //如果是数字，则直接进入数字栈
                digitBrackets.push(ch - '0');
            }else if(isOperator(ch)){ //如果是操作符
                if(operatorBrackets.isEmpty()){
                    //若栈空或者栈顶为(
                    operatorBrackets.push(ch);
                }else if(compareOperatorPriority(ch,operatorBrackets.peek())>0){
                    //若操作符优先级高
                    operatorBrackets.push(ch);
                }else {
                    while (compareOperatorPriority(ch,operatorBrackets.peek()) <= 0){
                        Character topOperator = operatorBrackets.pop();
                        Integer firstDigit = digitBrackets.pop();
                        Integer secondDigit = digitBrackets.pop();
                        Integer result = new Integer(0);
                        switch (topOperator){
                            case '+':result = secondDigit + firstDigit;break;
                            case '-':result = secondDigit - firstDigit;break;
                            case '*':result = secondDigit * firstDigit;break;
                            case '/':result = secondDigit / firstDigit;break;
                            default:break;
                        }
                        digitBrackets.push(result);
                        if(operatorBrackets.isEmpty()){
                            break;
                        }
                    }
                    operatorBrackets.push(ch);
                }
            }
        }
        while (!operatorBrackets.isEmpty()){
            Character operator = operatorBrackets.pop();
            Integer firstDigit = digitBrackets.pop();
            Integer secondDigit = digitBrackets.pop();
            Integer result = new Integer(0);
            switch (operator){
                case '+':result = secondDigit + firstDigit;break;
                case '-':result = secondDigit - firstDigit;break;
                case '*':result = secondDigit * firstDigit;break;
                case '/':result = secondDigit / firstDigit;break;
                default:break;
            }
            digitBrackets.push(result);
        }
        return digitBrackets.pop();
    }

    /**
     * 判断是否为数字
     * */
    static boolean isDigit(char c){
        int a = c - '0';
        if(a >= 0 && a <= 9){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断是否为操作符
     * */
    static boolean isOperator(char c){
        if( c == '+' || c == '-' || c == '*' || c == '/'){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 比较两个操作符的优先级
     * op1比op2高，返回1；一样，返回0；低，返回-1
     * */
    static int compareOperatorPriority(char op1,char op2){
        switch(op1){
            case '+':
            case '-':
                return (op2 == '*' || op2 == '/'? -1:0);
            case '*': case '/':
                return (op2 == '-' || op2 == '+'? 1:0);
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            System.out.println("str的计算结果为:"+calculate(str));
        }
    }
}