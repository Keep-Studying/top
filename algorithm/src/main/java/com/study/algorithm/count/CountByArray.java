/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.count;

import java.io.*;

/**
 * 统计成绩-使用数组
 * @author study
 * @version : CountByArray.java, v 0.1 2020年07月04日 15:43 study Exp $
 */
public class CountByArray implements StatisticsScore{
    /**
     * 不输出排序好的内容到磁盘时，输出结果为：
     * 总共的数据大小: 2100001
     * 计算花费的时间为:16ms
     *
     * 输出时，输出结果为：
     * 总共的数据大小: 2100001
     * 计算花费的时间为:888ms
     * */
    public static void main(String[] args) throws IOException {
        String str = null;
        //数据总量是2100001
        String fileName = "E:\\IDEA_ITEM_TARGET\\200w.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName),"UTF-8");

        BufferedReader br = new BufferedReader(isr);
        int total = 0 ;
        //表示总数,总量是2100001
        int data[] = new int[2100002];
        //一行一行的读,将成绩全部读取到数据中 时间复杂度为 O(n)
        while((str = br.readLine()) != null){
            double score = Double.valueOf(str);
            //成绩只有2位小数，放大100倍，变为整数
            score = score * 100;
            data[total++] = (int)score ;
            // System.out.println((int) score);
        }
        System.out.println("总共的数据大小: " + total);
        long start = System.currentTimeMillis();
        countSort(data,0,data.length-1);
        System.out.println("计算花费的时间为:" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void countSort(int data[],int min,int max) throws IOException {
        //对成绩进行排序用到的排序数组，初始值全为0
        int counts[] = new int[max + 1];
        for (int i = 0; i < data.length; i++) {
            //data[i]表示的是data数组中第i个元素的成绩
            // counts[data[i]]的表示的是这个成绩的的有多少人
            counts[data[i]]++;
        }
        /**输出排序好的内容到磁盘时*/
        File file = new File("E:\\IDEA_ITEM_TARGET\\200w-arraysort.txt");
        Writer out = new FileWriter(file);
        //对排序数组进行遍历，下标i代表放大100倍的成绩，counts[i]代表个数
        for (int i = 0; i <= max; i++) {
            if(counts[i] > 0){
                for (int j=0;j < counts[i];j++){
                    out.write(((double)(i / 100.0))+"\r\n");
                }
            }
        }
        out.close();
    }
}