/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.Scanner;
import java.util.Stack;

/**
 * NowCoderHj70
 *
 * 描述
 * 矩阵乘法的运算量与矩阵乘法的顺序强相关。
 * 例如：
 *
 * A是一个50×10的矩阵，B是10×20的矩阵，C是20×5的矩阵
 *
 * 计算A*B*C有两种顺序：((AB)C)或者(A(BC))，前者需要计算15000次乘法，后者只需要3500次。
 *
 * 编写程序计算不同的计算顺序需要进行的乘法次数。
 *
 * 数据范围：矩阵个数：1\le n\le 15 \1≤n≤15 ，行列数：1\le row_i,col_i\le 100\1≤row
 * i
 * ​
 *  ,col
 * i
 * ​
 *  ≤100 ，保证给出的字符串表示的计算顺序唯一。
 * 进阶：时间复杂度：O(n)\O(n) ，空间复杂度：O(n)\O(n)
 *
 * 输入描述：
 * 输入多行，先输入要计算乘法的矩阵个数n，每个矩阵的行数，列数，总共2n的数，最后输入要计算的法则
 * 计算的法则为一个字符串，仅由左右括号和大写字母（'A'~'Z'）组成，保证括号是匹配的且输入合法！
 *
 * 输出描述：
 * 输出需要进行的乘法次数
 * @author boyan
 * @version : NowCoderHj70.java, v 0.1 2023-01-05 15:39 boyan
 */
public class NowCoderHj70 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt();
            int a[][] = new int[n][2];
            for (int i = 0; i < n; i++) {
                a[i][0] = in.nextInt();
                a[i][1] = in.nextInt();
            }
            String s =in.next();
            // 存放矩阵行数和列数
            Stack<Integer> stack = new Stack<>();
            int sum = 0;
            for (int i = s.length() -1,j = n-1; i >= 0 ; i--) {
                if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                    // 属于字母则把相应的矩阵列数和行数入栈
                    stack.push(a[j][1]);
                    stack.push(a[j][0]);
                    j--;
                }else if (s.charAt(i) == '('){
                    // 括号：推出计算
                    // 矩阵尺寸x0*y0
                    int x0 = stack.pop(),y0 = stack.pop();
                    // 矩阵尺寸x1*y1
                    int x1 = stack.pop(),y1 = stack.pop();
                    // 两个矩阵的乘法次数为x0*y0*y1或x0*x1*y1（其中y0==x1）
                    sum += x0 * y0 * y1;
                    // 把相乘后得到的矩阵列数入栈
                    stack.push(y1);
                    stack.push(x0);
                }
            }
            System.out.println(sum);
        }
    }

}