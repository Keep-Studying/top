/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.heap;

import java.util.Arrays;
import java.util.Random;

/**
 * TopK问题
 * 给你1亿个不重复的数字（整数，1~2^32-1），求出top10。前10大的数字，
 * 还可动态添加新数字，但总个数不会超过1亿。
 * @author study
 * @version : TopK.java, v 0.1 2020年07月13日 22:06 study Exp $
 */
public class TopK {

    private int k = 10;
    private int data[] = new int[k];

    /**
     * 亿级 插入平均算O(n)，
     * 小顶堆，每次只需要比较堆顶即可，比堆顶大我们就替换堆顶，
     * 然后做一次堆化，堆化的时间复杂度是logn
     *
     * 空间复杂度为：S(n) = O(n),n即top n,n为常量，也可以说是O(1)
     * */
    public void topK() {
        Random r = new Random();
        long time = System.currentTimeMillis();
        int size = 0;
        boolean init = false;
        /**
         * 时间复杂度为:T(n) = O(n)
         * */
        for (int i = 0; i < 100000000; i++) {
            int num = r.nextInt(1000000000);
            if (size < k) {
                data[size] = num;
                size++;
            } else {
                if (!init) { // 初始化堆，这里我们只需要初始化一次就可以了
                    for (int j = k / 2 - 1; j >= 0; j--) {
                        /**
                         * 堆化，时间复杂度为:T(n) = O(logn)
                         * */
                        minHeap(data, 0, k);
                    }
                    init = true;
                }
                if (num > data[0]) { // 小顶堆那么堆顶是最小的，如果当前的数比堆顶大，则替换堆顶，然后做一次堆化
                    data[0] = num;
                    /**
                     * 堆化，时间复杂度为:T(n) = O(logn)
                     * */
                    minHeap(data, 0, k);
                }
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - time) + "ms");
        System.out.println(Arrays.toString(data));


    }

    /**
     * 构建一个小顶堆
     * */
    public void minHeap(int data[], int start, int end) { // 构建一个小顶堆 从上往下构建
        int parent = start;
        int son = parent * 2 + 1; // 下标是从0开始的就要加1，从1就不用
        while (son < end) {
            int temp = son;
            // 比较左右节点和父节点的大小
            if (son + 1 < end && data[son] > data[son + 1]) { // 表示右节点比左节点小
                temp = son + 1; // 就要换右节点跟父节点
            }
            // temp表示的是 我们左右节点小的那一个
            if (data[parent] < data[temp])
                return; // 不用交换
            else { // 交换
                int t = data[parent];
                data[parent] = data[temp];
                data[temp] = t;
                parent = temp; // 继续堆化
                son = parent * 2 + 1;
            }
        }
        return;
    }

    /**
     * 输出结果示例：
     * 耗时：1632ms
     * [999999853, 999999892, 999999884, 999999899, 999999979, 999999944, 999999897, 999999934, 999999910, 999999998]
     * */
    public static void main(String[] args) {
        TopK topK = new TopK();
        topK.topK();
    }
}