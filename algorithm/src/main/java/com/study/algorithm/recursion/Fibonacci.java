/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.recursion;

import org.junit.Test;

/**
 * 斐波那契数列递归调用
 *
 * 1 1 2 3 5 8 13 21 ...
 * 求解公式为：f(n) = f(n-1) + f(n-2)
 * 终止条件：n <= 2时，f(n) = 1
 * @author study
 * @version : Fibonacci.java, v 0.1 2020年07月01日 0:06 study Exp $
 */
public class Fibonacci {

    /**做缓存使用，用来保存中间运算结果，初始化全部都是0*/
    private int data[];

    /**
     * 普通递归调用
     * 时间复杂度: T(n) = O(2^n)
     * 空间复杂度：S(n) = O(2^n)
     * */
    public int fab(int n){
        //递归的终止条件
        if(n <= 2){
            return 1;
        }
        return fab(n -1) +fab(n -2);
    }
    /**
     * 耗时较长，
     * 输出结果如下：
     * ...
     * 45:1134903170 所花费的时间为3719ms
     * 当数字较大时，会报java.lang.StackOverflowError
     * */
    @Test
    public void testFab(){
        for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + fab(i) + " 所花费的时间为"
                    + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**递归优化*/

    /**
     * case1：使用非递归：所有的递归代码理论上都是可以转换成非递归的
     * 时间复杂度: T(n) = O(n)
     * 空间复杂度：S(n) = O(1)
     * */
    public int noFab(int n){
        if(n <= 2){
            return 1;
        }
        //f(n-1)
        int fn_1 = 1;
        //f(n-2)
        int fn_2 = 1;
        //f(n)
        int fn = 0;
        for (int i = 3; i <= n; i++) {
            //f(n) = f(n-1) + f(n-2)
            fn = fn_1 + fn_2;
            fn_1 = fn_2;
            fn_2 = fn;
        }
        return fn;
    }
    /**
     * 输出结果如下：
     * ...
     * 45:1134903170 case1 所花费的时间为0ms
     * */
    @Test
    public void testNoFab(){
        for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + noFab(i) + " case1 所花费的时间为"
                    + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * case2：加入缓存：把中间运算结果保存起来，这样就可以把递归的复杂度降为O(n)
     * 时间复杂度: T(n) = O(n)
     * 空间复杂度：S(n) = O(n)
     * */
    public int fabWithCache(int n){
        if(n <= 2){
            return 1;
        }
        if(data[n] >0){
            return data[n];
        }
        int result = fabWithCache(n - 1) + fabWithCache(n -2);
        data[n] = result;
        return result;
    }
    /**
     * 输出结果如下：
     * ...
     * 45:1134903170 case2 所花费的时间为0ms
     * */
    @Test
    public void testFabWithCache(){
        for (int i = 1; i <= 45; i++) {
            data = new int[46];
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + fabWithCache(i) + " case2 所花费的时间为"
                    + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * case3：尾递归
     * 尾递归就是调用函数一定出现在末尾，没有任何其他的操作了
     * 因为我们编译器在编译代码时，如果发现函数末尾已经没有操作了，这时候就不会创建新的栈，而且覆盖到前面去
     * 时间复杂度: T(n) = O(n)
     * 空间复杂度：S(n) = O(n)
     * @param pre 上一次运算的结果
     * @param res 上上次运算的结果
     * @param n   循环值
     * */
    public int tailFab(int pre,int res,int n){
        //递归的终止条件
        if(n <= 2){
            return res;
        }

        return tailFab(res,pre+res,n-1);//JDK源码中会有类似代码
    }
    /**
     * 输出结果如下：
     * ...
     * 45:1134903170 case3 所花费的时间为0ms
     * */
    @Test
    public void testTailFab(){
        for (int i = 1; i <= 45; i++) {
            data = new int[46];
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + tailFab(1,1,i) + " case3 所花费的时间为"
                    + (System.currentTimeMillis() - start) + "ms");
        }
    }
}