/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.count;

import java.io.*;

/**
 * 统计成绩-使用归并排序
 * @author study
 * @version : CountByMergeSort.java, v 0.1 2020年07月04日 16:28 study Exp $
 */
public class CountByMergeSort {


    private static double temp [];

    /**
     * 使用一个通用的临时数组，且输出结果到磁盘，输出结果
     * 总共的数据大小: 2100001
     * 计算花费的时间为:1333ms
     *
     * 使用一个通用的临时数组，且不输出结果到磁盘，输出结果
     * 总共的数据大小: 2100001
     * 计算花费的时间为:406ms
     *
     * 每次都去开一个临时数组，输出结果（总共的数据大小: 2100001输出后，迟迟没有花费时间输出）
     * 总共的数据大小: 2100001
     *
     * */
    public static void main(String[] args) throws Exception {

        String str = null;
        String fileName = "E:\\IDEA_ITEM_TARGET\\200w.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        double data[] = new double[2100002];
        int i = 0;
        while ((str = br.readLine()) != null) {
            data[i++] = Double.valueOf(str);
        }
        System.out.println("总共的数据大小: " + i);
        long start = System.currentTimeMillis();
        // Arrays.sort(data);
        temp = new double[data.length];
        mergeAndSort(data, 0, data.length - 1);
/*        File file = new File("E:\\IDEA_ITEM_TARGET\\200w-mergeSort.txt");
        Writer out = new FileWriter(file);
        for (i = 0; i < data.length; i++) {
            out.write(String.valueOf(data[i]) + "\r\n");
        }
        out.close();*/
        System.out.println("计算花费的时间为:" + (System.currentTimeMillis() - start) + "ms");

        // System.out.println(Arrays.toString(data));
        // //JDK里面的排序源码会有归并排序

    }

    /**
     * 归并排序
     * @param data
     * @param left
     * @param right
     * */
    public static void mergeAndSort(double data[],int left,int right){
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
    public static void merge(double data[],int left,int mid,int right){
        //借助一个临时数组用来保存合并的数据，
        // 不要每次都去开一个临时数组，开一个通用的
        //double temp[] = new double[data.length];
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
}