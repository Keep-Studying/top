/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.arrays;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 统计随机年龄
 *
 * 给你一个文件里面包含全国人民（14亿）的年龄数据（0~180），现在要你统计每一个年龄有多少人？
 * 给定机器为 单台+2CPU+2G内存。不得使用现成的容器，比如map等。【指定的话就不考虑使用分布式去解决】
 *
 * 在以上情况下你该如何以最高效的方法来解决这个问题？
 * 排序算法：1 1 1 2 2 2  3 3 3 4 4 5。
 * 想过没？能不能解决这个问题？：不能
 * 为什么？排序的最高效算法：O(nlogn) 14亿，排不出来，而且内存也不够。
 * 数组算法：
 * int a[] = new int[180];//表示年龄的范围为0~179岁
 * a[0]++;//0表示的是0岁，a[0]的值表示的是0岁的有多少人
 *
 * @author study
 * @version : StatisticsRandomAge.java, v 0.1 2020年07月02日 22:56 study Exp $
 */
public class StatisticsRandomAge {

    /**
     * 输出结果：
     * 总共的数据大小: 1400000000
     * 0:7773919
     * 1:7782244
     * 2:7779534
     * ...
     * 179:7774739
     * 计算花费的时间为:86424ms
     * */
    public static void main(String[] args) throws Exception {
        String str = null;
        String fileName = "E:\\age1.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName),"UTF-8");

        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(isr);
        int total = 0 ;
        //表示年龄的范围为0~179岁
        int data[] = new int[180];
        //一行一行的读 时间复杂度为 O(n)
        while((str = br.readLine()) != null){
            int age = Integer.valueOf(str);
            //0表示的是0岁，a[0]的值表示的是0岁的有多少人
            data[age]++ ;
            total++ ;
        }
        //O(n) 14亿.
        // 计算机最差的也要100万/秒 *1000 = 10亿
        // 则花费的时间在100~1000s之间 => 500s以下 60*8=480s
        System.out.println("总共的数据大小: " + total);

        //下标从0开始的
        for(int i = 0 ; i < data.length ; i ++){
            System.out.println(i + ":" + data[i]);
        }
        ////实际花费的时间 86424ms  => 86s
        System.out.println("计算花费的时间为:" + (System.currentTimeMillis() - start) + "ms");
    }
}