/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 * 自行引入下述maven jar包即可
 *        <dependency>
 *             <groupId>com.google.guava</groupId>
 *             <artifactId>guava</artifactId>
 *             <!--版本号可以自行选择更高的-->
 *             <version>19.0</version>
 *         </dependency>
 * @author study
 * @version : BloomFilterByJar.java, v 0.1 2020年07月19日 0:20 study Exp $
 */
public class BloomFilterByJar {
    public static void main(String[] args) {
        //我们要插入的数据也就是n,下述值为1亿
        int datasize = 100000000;
        // 误判率为0.1%
        double fpp = 0.001;

        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), datasize, fpp);

        long start = System.currentTimeMillis();
        for(int i = 0 ; i < 100000000 ; i ++) {
            bloomFilter.put(i);
        }
        System.out.println((System.currentTimeMillis() - start) + ":ms");

        //怎么测试这个误判率
        int t = 0;
        for(int i = 20000000 ; i < 30000000 ; i++) {
            if (bloomFilter.mightContain(i)) { //表示存在 t ++; } }
                t++;
            }
        }
        System.out.println("真实误判的个数为："+t);
    }
}