/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 堆是什么？堆是一种特殊的树，需要满足以下两点：
 * 1. 是一颗完全二叉树
 * 2. 其每一个结点的值都大于等于或者小于等于其左右子结点的值
 *
 * 完全二叉树：除了最后一层外，求他层每个节点都是满的，且最后一层的节点都要靠左排列
 * 堆的插入操作，有两种实现方式：
 * 1. 从下往上：假如我们在上图中插入9，很显然，此时插入9后，是不满足堆树的性质的，怎么办呢？
 *             其实只需要做下交换就可以了，直到依次往上做到不能交换位置
 * 2. 从上往下：其实就是把插入的点放到堆顶，然后依次往下比较即可
 *
 * 堆排序：
 * 利用堆树进行排序：
 * 1. 建堆：先按照序列顺序存储在完全二叉树中。
 * 2. 从最后一个非叶子节点堆化
 * @author study
 * @version : HeapSort.java, v 0.1 2020年07月11日 19:04 study Exp $
 */
public class HeapSort {

    /**
     * 堆化大顶堆
     * */
    public static void maxHeap(int data[],int start,int end){
        int parent = start;
        // 下标是从0开始的就要加1，从1就不用
        int son = parent * 2 + 1;
        while (son < end){
            //比较左右节点和父节点的大小
            //默认是左节点比较大
            int temp = son;
            // 表示右节点比左节点到
            if(son + 1 < end && data[son] < data[son+1]){
                // 就要换右节点跟父节点
                temp = son + 1;
            }
            //temp表示左右结点中较大的那一个
            if(data[parent] > data[temp]){
                return;
            }else {
                int t = data[parent];
                data[parent] = data[temp];
                data[temp] = t;
                //继续堆化
                parent = temp;
                son = parent * 2 + 1;
            }
        }
        return;
    }

    /**
     * 堆排序
     * 时间复杂度：T(n) = O(nlogn)
     *
     * */
    public static void heapSort(int data[]){
        int len = data.length;
        //从最后一个非叶子节点开始堆化
        for (int i = len / 2 -1;i >= 0; i--){//O(nlogn)
            maxHeap(data,i,len);
        }
        for (int i = len - 1;i > 0;i--){//O(nlogn)
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            //这个i能不能理解？因为len~i已经排好序了
            maxHeap(data,0,i);
        }
    }

    /**
     * 输出结果：
     * [1, 3, 4, 7, 8, 14, 17, 20, 25]
     * */
    public static void main(String[] args) {
        int data[] = { 8, 4, 20, 7, 3, 1, 25, 14, 17 };
        heapSort(data);
        System.out.println(Arrays.toString(data));
    }
}