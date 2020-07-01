/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 插入排序算法思想：往一个有序的集合里面插入元素，插入后序列仍然有序这就是。
 *
 * 实现思路
 * 1.将数组分成已排序和未排序段，初始化时已排序段只有一个元素
 * 2.到未排序段取元素插入到已排序段，并且保证插入后仍然有序
 * 3.重复执行上述操作，知道未排序段元素全部加完
 *
 * 可以使用的数据结构
 * *数组【1个】
 * *链表
 * @author study
 * @version : InsertSort.java, v 0.1 2020年07月01日 22:16 study Exp $
 */
public class InsertSort {
    public static void main(String[] args) {
        int data[] = {9,8,7,0,1,3,2};
        //数组长度
        int n = data.length;

        /**
         * 时间复杂度:T(n) = O(n^2)
         * 外层循环必然是：O(n)
         * 最好的情况是:序列天然有序,内层循环全部走的break逻辑，O(1)
         * 最差的情况是：序列是倒序的，内层循环数据都要往后移动，O(n)
         * */
        //为什么i要从1开始？ 第一个不用排序，我们就把数组从i分开，0~i的认为已经排好序
        for (int i = 1; i < n; i++) {
            //当前元素
            int temp = data[i];
            //当前元素的前一位元素下标
            int j = i -1 ;
            //为什么要从后往前排序呢？ 因为从后往前排，数据移动比较方便，直接往后移动一位即可
            //如果从前往后排，for(int k = 0;k<=j;k++)，数据移动的时候有可能需要将排好序的内容整体往后移
            for (; j >= 0; j--) {//从尾到头 1+2+3+4+5+...+n=>
                if(data[j] > temp){
                    //data[j]比temp大，则将data[j]向后移动
                    data[j+1] = data[j];
                }else {//因为前面都是已经排好序的，那么找到一个比temp小的元素，就不必再向前找了，前面的元素只会更小
                    break;//O(1)	如果这个break执行的越多 那么我是不是效率就越高?
                }
            }
            //为什么赋值是data[j+1] = data，因为比较时，j已经是减过1了,向后移动时，需要再加1
            data[j+1] = temp;
            System.out.print("第" +i +"次的排序结果为：");
            System.out.println(Arrays.toString(data));
        }
    }
}