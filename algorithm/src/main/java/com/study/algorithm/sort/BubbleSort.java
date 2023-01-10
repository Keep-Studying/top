/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 * 冒泡排序只会操作相邻的两个数据，每次冒泡操作都会对相邻的两个元素进行比较，
 * 看是否满足大小关系要求。如果不满足，就让它两互换，一次冒泡会让至少一个元
 * 素移动到它应该在的位置，重复n次，就完成了n个数据的排序工作
 *
 * 时间复杂度：T(n)=O(n^2)
 * 空间复杂度：S(n)=O(n)
 * 交换次数：挺大的
 * 稳定性：稳定
 *
 * @author study
 * @version : BubbleSort.java, v 0.1 2020年07月02日 22:06 study Exp $
 */
public class BubbleSort {

    /**
     * 输出结果：
     * 排序前:[4, 5, 6, 3, 2, 1]
     * 第0次:[4, 5, 3, 2, 1, 6]
     * 第1次:[4, 3, 2, 1, 5, 6]
     * 第2次:[3, 2, 1, 4, 5, 6]
     * 第3次:[2, 1, 3, 4, 5, 6]
     * 第4次:[1, 2, 3, 4, 5, 6]
     * 排序后:[1, 2, 3, 4, 5, 6]
     * */
    @Test
    public void testBubbleSort(){
        //无break执行的示例
        int data[] = {4,5,6,3,2,1};
        //会有break执行的示例
        //int data[] = {5,4,6};
        int n = data.length;
        System.out.println("排序前:"+ Arrays.toString(data));
        for (int i = 0; i < n - 1; i++) {
            /**冒泡排序优化点：增加标识符，如果下述循环执行完仍为false，则序列已经是有序的了*/
            boolean flag = false;
            for (int j = 0; j < n -1 -i; j++) {
                if(data[j] > data[j+1]){
                    int temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                    flag =true;
                }
            }
            System.out.println("第"+i+"次:"+Arrays.toString(data));
            if (!flag){
                System.out.println("第"+i+"次执行时,break");
                break;
            }
        }
        System.out.println("排序后:"+Arrays.toString(data));
        bubbleSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void bubbleSort(int[] nums){
        if (nums == null || nums.length == 0){
            return;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            boolean flag = false;
            for (int j = 0; j < length-1-i; j++) {
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }
}