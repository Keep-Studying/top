/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序
 * 快速排序是C.R.A.Hoare于1962年提出的一种划分交换排序。它采用了一种分治的策略，
 * 通常称其为分治法(Divide-and-ConquerMethod)。
 *
 * 该方法的基本思想是：
 * 1．先从数列中取出一个数作为基准数。
 * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 * 3．再对左右区间重复第二步，直到各区间只有一个数。
 *
 * 即挖坑填数+分治法
 * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
 * 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
 * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
 * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
 *
 * 时间复杂度：T(n)=O(n^2)
 * 空间复杂度：S(n)=O(n)
 * 稳定性：不稳定
 *
 * <Strong>其优化就是优化基准数，提供一个取三个数中间的思路<Strong/>
 * @author study
 * @version : QuicklySort.java, v 0.1 2020年07月02日 22:11 study Exp $
 */
public class QuicklySort {
    public void quicklySort(int data[],int left,int right){
        //基准数，取序列的第一个，不能用data[0]
        int base = data[left];
        //表示的是从左边找的位置
        int ll = left;
        //表示的是从右边找的位置
        int rr = right;
        while (ll < rr){
            //从后面往前找比基准数小的数
            while (ll < rr && data[rr] >= base){
                rr--;
            }
            //表示的是找到有比之大的
            if(ll < rr){
                //交换方式
                int temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                //填坑方式
                //data[ll] = data[rr]; //将data[rr]填到data[ll]中，data[rr]就形成了一个新的坑
                ll++;
            }
            //从前往后找比基准数小的
            while (ll < rr && data[ll] <= base){
                ll++;
            }
            //表示的是找到有比之小的
            if (ll < rr) {
                //交换方式
                int temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                //填坑方式
                //data[rr] = data[ll];//将data[ll]填到data[rr]中，data[ll]就形成了一个新的坑
                rr--;
            }
        }
        //填坑方式，使用该行代码：退出时，ll等于rr。将base填到这个坑中。
        //data[ll] = base;
        //肯定是递归，左右继续快排，注意要加条件不然递归就栈溢出了
        if(left < ll){
            quicklySort(data,left,ll-1);
        }
        if(ll < right){
            quicklySort(data,ll+1,right);
        }
    }

    /**
     * 输出结果：
     * [10, 16, 28, 45, 50, 80, 90, 100]
     * */
    @Test
    public void testQuicklySort(){
        int data[] = {45,28,80,90,50,16,100,10};
        quicklySort(data,0,data.length-1);
        System.out.println(Arrays.toString(data));
    }

}