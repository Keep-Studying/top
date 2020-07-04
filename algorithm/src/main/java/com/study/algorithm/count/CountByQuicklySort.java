/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.count;

import java.io.*;

/**
 * 统计成绩-使用快速排序
 * @author study
 * @version : CountByQuicklySort.java, v 0.1 2020年07月04日 16:52 study Exp $
 */
public class CountByQuicklySort implements StatisticsScore {
    /**
     * 输出排序结果到磁盘，输出结果：
     * 总共的数据大小:2100001
     * 计算花费的时间为:1453ms
     *
     * 不输出排序结果到磁盘，输出结果：
     * 总共的数据大小:2100001
     * 计算花费的时间为:328ms
     * */
    public static void main(String[] args) throws Exception {

        //int data[] = { 4, 5, 6, 3, 2, 1 };
        String str = null;
        String fileName = "E:\\IDEA_ITEM_TARGET\\200w.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        double data[] = new double[2100002];
        int i = 0;
        while ((str = br.readLine()) != null) {
            data[i++] = Double.valueOf(str);
        }
        System.out.println("总共的数据大小:" + i);
        long start = System.currentTimeMillis();
        qSort(data, 0, data.length - 1);

/*		File file =new File("E:\\IDEA_ITEM_TARGET\\200w-quicklySort.txt");
		Writer out =new FileWriter(file);
		for(i = 0 ; i< data.length ; i++) {
			out.write(String.valueOf(data[i]) + "\r\n");
		}
		out.close();*/
        System.out.println("计算花费的时间为:" + (System.currentTimeMillis() - start) + "ms");
        //System.out.println(Arrays.toString(data));
    }

    public static void qSort(double data[], int left, int right) {

        double base = data[left];
        int ll = left;
        int rr = right;
        while (ll < rr) {
            while (ll < rr && data[rr] >= base) {
                rr--;
            }
            if (ll < rr) {
                double temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                ll++;
            }
            while (ll < rr && data[ll] <= base) {
                ll++;
            }
            if (ll < rr) {
                double temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                rr--;
            }
        }
        if (left < ll - 1)
            qSort(data, left, ll - 1);
        if (ll + 1 < right)
            qSort(data, ll + 1, right);
    }
}