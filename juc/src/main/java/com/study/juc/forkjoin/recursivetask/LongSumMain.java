/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.forkjoin.recursivetask;

import com.study.juc.utils.RandomUtils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author study
 * @version : LongSumMain.java, v 0.1 2020年09月05日 14:56 study Exp $
 */
public class LongSumMain {
    //获取逻辑处理器的数量
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    //for time conversion
    static final long NPS = (1000L * 1000 * 1000);

    static long calcSum;

    static final boolean reportSteals = true;

    public static void main(String[] args) throws Exception{
        int[] array = RandomUtils.buildRandomIntArray(20000000);
        System.out.println("cpu num is :"+NCPU);

        //单线程下计算数组数据总和
        long start = System.currentTimeMillis();
        calcSum = seqSum(array);
        System.out.println("seq sum=" + calcSum);
        System.out.println("single thread sort:->"+(System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        //采用fork/join方式将数组求和任务进行拆分执行，最后合并结果
        LongSum longSum = new LongSum(array, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool(NCPU); //使用的线程数
        ForkJoinTask<Long> task = forkJoinPool.submit(longSum);
        System.out.println("forkjoin sum=" + task.get());
        System.out.println("fork join pool sort:->"+(System.currentTimeMillis()-start));

        if(task.isCompletedAbnormally()){
            System.out.println(task.getException());
        }

        forkJoinPool.shutdown();
    }

    static long seqSum(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; ++i)
            sum += array[i];
        return sum;
    }
}