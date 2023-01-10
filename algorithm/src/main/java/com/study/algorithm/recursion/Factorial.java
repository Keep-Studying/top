/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.recursion;

import org.junit.Test;

/**
 * 求数字N的阶乘运算，如
 * 5! = 5*4*3*2*1
 * 求解公式：f(n) = n * f(n-1)
 * 终止条件：f(1) = 1
 * @author study
 * @version : Factorial.java, v 0.1 2020年07月01日 0:43 study Exp $
 */
public class Factorial {

    /**
     * 普通递归
     * */
    public int fac(int n) {
        //终止条件
        if (n <= 1)
            return 1;
        return n * fac(n - 1);
    }
    @Test
    public void testFac(){
        System.out.println(fac(5));
    }

    public int tailFac(int n,int res) {
        System.out.println(n + ":" + res);
        if(n <= 1){
            return res;
        }
        return tailFac(n-1,n * res);
    }
    @Test
    public void testTailFac(){
        System.out.println(tailFac(5,1));
    }


    @Test
    public void test002(){
        int fnn = fnn(3);
        System.out.println(fnn);
    }

    public  int fnn(int n){
        int result = 1;
        int fn = 1 ;
        for (int i = 1; i <= n; i++) {
            fn  = fn * i;
            result = result * fn;
        }
        return result;
    }
}