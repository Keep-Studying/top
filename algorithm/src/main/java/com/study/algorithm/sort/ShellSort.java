/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import org.junit.Test;

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
 *
 * 时间复杂度：T(n)=O(n^2)
 * 空间复杂度：S(n)=O(n)
 * 稳定性：不稳定
 * @author study
 * @version : ShellSort.java, v 0.1 2020年07月02日 8:14 study Exp $
 */
public class ShellSort {

    /**
     * 输出结果：
     * 排序之前：[7, 8, 9, 0, 4, 3, 1, 2, 5, 10]
     * 增量为5,第0次,排序结果为：[3, 8, 9, 0, 4, 7, 1, 2, 5, 10]
     * 增量为5,第1次,排序结果为：[3, 1, 9, 0, 4, 7, 8, 2, 5, 10]
     * 增量为5,第2次,排序结果为：[3, 1, 2, 0, 4, 7, 8, 9, 5, 10]
     * 增量为5,第3次,排序结果为：[3, 1, 2, 0, 4, 7, 8, 9, 5, 10]
     * 增量为5,第4次,排序结果为：[3, 1, 2, 0, 4, 7, 8, 9, 5, 10]
     * 增量为2,第0次,排序结果为：[2, 1, 3, 0, 4, 7, 5, 9, 8, 10]
     * 增量为2,第1次,排序结果为：[2, 0, 3, 1, 4, 7, 5, 9, 8, 10]
     * 增量为1,第0次,排序结果为：[0, 1, 2, 3, 4, 5, 7, 8, 9, 10]
     * 耗时0ms
     * 排序之后：[0, 1, 2, 3, 4, 5, 7, 8, 9, 10]
     * */
    @Test
    public void testShellSort1(){
        int[] array={7,8,9,0,4,3,1,2,5,10};
        System.out.println("排序之前："+ Arrays.toString(array));
        //希尔排序
        int gap = array.length;
        long start = System.currentTimeMillis();
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
                System.out.println("增量为"+gap+",第"+i+"次,排序结果为："+ Arrays.toString(array));
            }
            if (gap == 1)
                break;
        }
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"ms");
        System.out.println("排序之后："+ Arrays.toString(array));
    }

    /**
     * 输出结果：
     * 增量为3,第0次,排序结果为：[0, 8, 7, 9, 1, 3, 2]
     * 增量为3,第1次,排序结果为：[0, 1, 7, 9, 8, 3, 2]
     * 增量为3,第2次,排序结果为：[0, 1, 3, 9, 8, 7, 2]
     * 增量为3,第3次,排序结果为：[0, 1, 3, 2, 8, 7, 9]
     * 增量为1,第0次,排序结果为：[0, 1, 3, 2, 8, 7, 9]
     * 增量为1,第1次,排序结果为：[0, 1, 3, 2, 8, 7, 9]
     * 增量为1,第2次,排序结果为：[0, 1, 2, 3, 8, 7, 9]
     * 增量为1,第3次,排序结果为：[0, 1, 2, 3, 8, 7, 9]
     * 增量为1,第4次,排序结果为：[0, 1, 2, 3, 7, 8, 9]
     * 增量为1,第5次,排序结果为：[0, 1, 2, 3, 7, 8, 9]
     * 耗时0ms
     * 排序后的数组:[0, 1, 2, 3, 7, 8, 9]
     * */
    @Test
    public void testShellSort2(){
        int a[] = { 9, 8, 7, 0, 1, 3, 2 };
        int n = a.length;
        long start = System.currentTimeMillis();
        for (int add = n / 2; add >= 1 ; add /= 2) {
            //打印计数使用
            int count = 0;
            for (int i = add; i < n; i++) {
                int data = a[i];
                int j = i -add;
                for (; j >= 0; j -= add) {
                    if(a[j] > data){
                        a[j+add] = a[j];//数据后移
                    }else {
                        break;
                    }
                }
                a[j+add] = data;
                System.out.println("增量为"+add+",第"+(count++)+"次,排序结果为："+ Arrays.toString(a));
            }
        }
        System.out.println("耗时"+(System.currentTimeMillis() - start)+"ms");
        System.out.println("排序后的数组:"+Arrays.toString(a));
    }
}