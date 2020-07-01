/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 归并排序
 * 归并排序（Merge）是将两个（或两个以上）有序表成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列都是有序的，然后再把有序子序列合并为整体有序序列
 * 归并排序是建立在归并操作上的一种有效的排序算法，该算法是采用分治的一个非常典型的应用。又称为2路归并
 * @author study
 * @version : MergeSort.java, v 0.1 2020年07月01日 23:50 study Exp $
 */
public class MergeSort {

    /**
     * 归并排序
     * @param data
     * @param left
     * @param right
     * */
    public void mergeAndSort(int data[],int left,int right){
        //终止条件为：left == right
        //相等了就表示只有一个数了 不用再拆了
        if(left < right){
            int mid = (left + right) / 2;
            //分成左右两路
            mergeAndSort(data,left,mid);
            mergeAndSort(data,mid+1,right);

            // 分完了 接下来就要进行合并，也就是我们递归里面归的过程
            merge(data,left,mid,right);
        }
    }

    /**
     * 合并
     * @param data
     * @param left
     * @param mid
     * @param right
     * */
    public void merge(int data[],int left,int mid,int right){
        //借助一个临时数组用来保存合并的数据
        int temp[] = new int[data.length];
        //表示的是左边的第一个数的位置
        int point1 = left;
        //表示的是右边的第一个数的位置
        int point2 = mid + 1;
        //表示的是我们当前已经到了哪个位置了
        int location = left;
        while (point1 <= mid && point2 <= right){
            if(data[point1] < data[point2]){
                temp[location] = data[point1];
                point1++;
                location++;
            }else {
                temp[location] = data[point2];
                point2++;
                location++;
            }
        }

        while (point1 <= mid){
            temp[location++] = data[point1++];
        }

        while (point2 <= right){
            temp[location++] = data[point2++];
        }
        for (int i = left; i <= right; i++) {
            data[i] = temp[i];
        }
    }

    @Test
    public void testMergeAndSort(){
        int data[] = { 9, 5, 6, 8, 0, 3, 7, 1 };
        mergeAndSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
        //JDK里面的排序源码会有归并排序
    }
}