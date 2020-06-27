/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.time;

import org.junit.Test;

/**
 * @author study
 * @version : TimeAndSpace.java, v 0.1 2020年06月27日 16:56 study Exp $
 */
public class TimeAndSpace {

    /**时间复杂度  start*/

    /**
     * 常数：O(1),所有能确定的数字我们都用O(1)，如O(1000)即也是O(1)
     * */
    @Test
    public void test01(){
        //运行1次，时间复杂度为O(1)
        int a = 1;

        //for这行代码会运行4次，第4次的时候结束循环跳出，i=3(0 1 2 3)
        for (int i = 0; i < 3; i++) {
            //这行代码会运行3次，时间复杂度为O(1)
            a = a+1;
        }
    }

    /**
     * 对数：O(logn)
     * */
    @Test
    public void test02(){
        //表示n为未知
        int n = Integer.MAX_VALUE;
        int i = 1;

        while (i <= n){
            /**
             * i的值：2,4,8,16,32 -> 2^0,2^1,2^2,2^3,2^4,……,2^n
             * -> 即 2^x=n -> 求出x就是我们运行的次数 -> x = log2n
             * -> 计算机忽略掉常数 -> x=logn -> O(logn)
             * */
            i = i*2;
        }
        /**
         * 时间复杂度同为O(logn)还有二分查找
         * 示例如：在1~100中找到69这个数字
         * 第一次：(1+100)/2 = 50
         * 第二次：(50+100)/2 = 75
         *  正好是上述过程的逆过程
         * */
    }

    /**
     * 线性：O(n)
     * */
    @Test
    public void test03(){
        //表示n为未知
        int n = Integer.MAX_VALUE;
        int a = 0;
        for (int i = 0; i < n; i++) {
            /**
             * 运行了n次，时间复杂度为O(n)，
             * n一定是一个未知的数字，如果是已知的，则时间复杂度为O(1)
             * */
            a = a+1;
        }
    }

    /**
     * 线性对数：O(nlogn)
     * */
    @Test
    public void test04(){
        //表示n为未知
        int n = Integer.MAX_VALUE;
        int i = 1;

        //for这行代码会执行n次，时间复杂度为O(n)
        for (int j = 0; j < n; j++) {
            while (i <= n){
                /**
                 * i的值：2,4,8,16,32 -> 2^0,2^1,2^2,2^3,2^4,……,2^n
                 * -> 即 2^x=n -> 求出x就是我们运行的次数 -> x = log2n
                 * -> 计算机忽略掉常数 -> x=logn -> O(logn)
                 * */
                i = i*2;
            }
        }
        //则上述代码的事件复杂度为O(nlogn)
    }

    /**
     * 平方：O(n^2)，双层循环嵌套
     * N次方：O(n^n)，多层循环嵌套
     * */
    @Test
    public void test05(){
        //表示n为未知
        int n = Integer.MAX_VALUE;
        int a = 0;

        // for这行代码执行n次,O(n)
        for(int i = 0 ; i < n;i++){
            // for这行代码执行n次,O(n)
            for(int j = 0 ; j < n ;j ++){
                //运行次数为n*n，则时间复杂度为O(n^2)
                a = a +1;
            }
        }

        //如冒泡排序
        // for这行代码执行n次,O(n)
        for(int i = 0 ; i < n;i++){
            //运行n次（与上面的不同之处在于j=i）
            for(int j = i ; j < n ;j ++){
                /**
                 * 运行了多少次？
                 *
                 * 外面的循环次数是确定的 O(n) n次，1 2 3 4 。。。n
                 *  内层循环
                 *  i=n 运行1次
                 *  i=n-1 运行2次
                 *  .
                 *  .
                 *  i=1 运行n次
                 *  则内层循环运行次数为：
                 *  1+2+3+……+n-1+n =>n*(n+1)/2 => (n^2+n)/2=> O(n^2);
                 *  => 注意有个规律，有加减法的时候，找次数最高的那个
                 * */
                a = a +1;
            }
        }
    }

    /**时间复杂度  end*/

    /**空间复杂度  start*/

    /**
     * 空间复杂度
     * 1、空间复杂度分析的意义：找花了内存的地方。数据
     * */
    @Test
    public void test06(){
        /*int data[100];
        List<>; list.add();
        Map put
        Set add
        Queue*/
    }

    /**空间复杂度  end*/
}