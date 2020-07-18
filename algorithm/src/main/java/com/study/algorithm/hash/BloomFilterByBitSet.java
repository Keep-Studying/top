/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.hash;

import java.util.BitSet;

/**
 * 布隆过滤器
 * 布隆过滤器是一个非常巧妙的数据结构，在很多高并发大数据项目中都有应用，
 * 它的特点就是高效的查找和插入，它的核心一句话就是：我告诉你不存在的那
 * 就肯定不存在，但是告诉你存在，其实是可能存在。
 *
 * 布隆过滤器结构：它的结构和上篇文章中写的BitMap非常类似，它是由一个
 * 很长的bit数组以及k个hash函数组成的。
 *
 *
 * @author study
 * @version : BloomFilterByBitSet.java, v 0.1 2020年07月19日 0:04 study Exp $
 */
public class BloomFilterByBitSet {

    int    size;
    // 底层为bit数组,bitMap long /64 %64，其实还是BitMap
    BitSet bits;

    public BloomFilterByBitSet(int size) {
        this.size = size;
        bits = new BitSet(size);
    }

    public int hash_1(String key){
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash) % size;
    }

    public int hash_2(String key){
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash) % size;
    }

    public int hash_3(String key){
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash) % size;
    }

    /**
     * 插入：将一个插入的元素使用K个hash函数进行K次计算，将
     * 得到的Hash值所对应的bit数组下标置为1
     *
     * 时间复杂度为：T(n) = O(1)
     * */
    public void add(String key){
        int hash1 = hash_1(key);
        int hash2 = hash_2(key);
        int hash3 = hash_3(key);

        bits.set(hash1,true);
        bits.set(hash2,true);
        bits.set(hash3,true);
    }

    /**
     * 查找：跟插入一样的道理，将查找的元素使用K个函数进行K次计算，
     * 将得到的值找出对应的bit数组下标，判断是否为1，如果都为1则
     * 说明这个值可能在序列中，反之肯定不在序列中
     *
     * 为什么是可能存在呢？因为当前元素取hash的结果值，之前其他的
     * 元素可能出现过，即当前元素计算出来hash值，然后将对应位置标识
     * 为true或者1时，该位置已经被设置过了，但是该元素是未被放进来的
     *
     * 时间复杂度为：T(n)=O(1)
     * */
    public boolean find(String key){
        int hash1 = hash_1(key);
        if(!bits.get(hash1)){
            return false;
        }
        int hash2 = hash_2(key);
        if(!bits.get(hash2)){
            return false;
        }
        int hash3 = hash_3(key);
        if(!bits.get(hash3)){
            return false;
        }
        return true;
    }

    /**
     * 删除是不支持的
     * */

    /**
     * 输出结果：
     * 49
     * 1081142246
     * 2140438327
     * false
     * true
     * */
    public static void main(String[] args) {
        // O(1000000000)
        //8bit= 1byte
        BloomFilterByBitSet bloomFilter = new BloomFilterByBitSet(Integer.MAX_VALUE);	//21亿
        System.out.println(bloomFilter.hash_1("1"));
        System.out.println(bloomFilter.hash_2("1"));
        System.out.println(bloomFilter.hash_3("1"));


        bloomFilter.add("1111");
        bloomFilter.add("1123");
        bloomFilter.add("11323");

        System.out.println(bloomFilter.find("1"));
        System.out.println(bloomFilter.find("1123"));
    }
}