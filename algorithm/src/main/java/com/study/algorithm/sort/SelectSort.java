/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 选择排序的思路和插入排序非常类似，也分已排序和未排序区间，但选择排序每次
 * 都会从未排序区间中找到最小的元素，将其放到已排序区间的末位。但是不像插入
 * 排序会移动数组，选择排序会每次进行交换
 *
 * 时间复杂度：T(n)=O(n^2)
 * 空间复杂度：S(n)=O(n)
 * 交换次数：最好：0次，最差：1+2+3+..+(n-1)=n(n-1)/2次，即n^2
 * 稳定性：不稳定
 *
 * @author study
 * @version : SelectSort.java, v 0.1 2020年07月02日 21:23 study Exp $
 */
public class SelectSort {

    /**
     * 输出结果
     * 第0次：[1, 2, 5, 3, 4]
     * 第1次：[1, 2, 5, 3, 4]
     * 第2次：[1, 2, 3, 5, 4]
     * 第3次：[1, 2, 3, 4, 5]
     * 第4次：[1, 2, 3, 4, 5]
     * 排序后：[1, 2, 3, 4, 5]
     * */
    public static void main(String[] args) {
        int data[] = {2,1,5,3,4};
        //int data[] = {5,4,3,2,1};
        int n = data.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n ; j++) {
                if(data[j] < data[i]){
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    System.out.println(data[j]+" : "+data[i]);
                }
            }
            System.out.println("第"+i+"次："+ Arrays.toString(data));
        }
        System.out.println("排序后："+ Arrays.toString(data));
    }
}