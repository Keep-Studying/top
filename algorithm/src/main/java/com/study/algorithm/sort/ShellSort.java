/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序，
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减少至1时，整个文件恰好被分成一组，算法便终止
 *
 * 步骤如下：
 * 1. 先取一个小于n的整数d1作为第一个增量，把文件的全部记录分组，所有距离为d1的倍数的记录放到同一个组中。
 *    先在各组内进行直接插入排序；
 * 2. 然后，取第二个增量d2<d1重复上述的分组和排序
 * 3. 知道所取的增量等于1（<...<d2<d1），即所有记录放在同一个组中进行直接插入排序为止
 * @author study
 * @version : ShellSort.java, v 0.1 2020年07月02日 8:14 study Exp $
 */
public class ShellSort {

    public static void main(String[] args){
        int[] array={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("排序之前："+ Arrays.toString(array));
        //希尔排序
        int gap = array.length;
        while (true) {
            gap /= 2;   //增量每次减半
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < array.length; j += gap) {//这个循环里其实就是一个插入排序
                    int temp = array[j];
                    int k = j - gap;
                    while (k >= 0 && array[k] > temp) {
                        array[k + gap] = array[k];
                        k -= gap;
                    }
                    array[k + gap] = temp;
                }
                System.out.println("第："+i+"次，增加为"+gap+",排序结果为："+ Arrays.toString(array));
            }
            if (gap == 1)
                break;
        }

        System.out.println();
        System.out.println("排序之后："+ Arrays.toString(array));
    }
}