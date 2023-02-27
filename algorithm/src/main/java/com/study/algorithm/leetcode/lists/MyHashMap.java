/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.leetcode.lists;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * LeetCode767
 * 设计一个hashMap
 *

 作者：LeetCode-Solution
 链接：https://leetcode.cn/problems/design-hashmap/solution/she-ji-ha-xi-ying-she-by-leetcode-soluti-klu9/
 来源：力扣（LeetCode）
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @author Lenovo
 * @version : LeetCode767.java, v 0.1 2023-02-20 14:25 Lenovo
 */
public class MyHashMap {


    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Pair>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                return pair.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.key == key) {
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }



    private class HashEntry{

        private int key;
        private int value;
        HashEntry next;
        HashEntry(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    int capacity;
    HashEntry[] datas;
    MyHashMap(int capacity){
        this.capacity = capacity;
        this.datas = new HashEntry[4];

    }

    public int get1(int key) {
        int h = hash(key);
        HashEntry iterator = datas[h];
        while (iterator != null) {
            if (iterator.key == key) {
                return iterator.value;
            }
            iterator = iterator.next;
        }
        return -1;
    }

    public void put1(int key, int value) {
        int h = hash(key);
        HashEntry iterator = datas[h];
        if (iterator == null){
            datas[h] = new HashEntry(key, value);
            return;
        }
        while (iterator != null) {
            if (iterator.key == key) {
                iterator.value = value;
                return;
            }
            iterator = iterator.next;
        }
        iterator.next = new HashEntry(key, value);
    }

    public void remove1(int key) {
        int h = hash(key);
        HashEntry iterator = datas[h];
        if(iterator == null){
            return;
        }
        while (iterator != null) {
            if (iterator.key == key) {
                 iterator.key = iterator.next.key;
                 iterator.value = iterator.next.value;
                 iterator.next = iterator.next.next;
                 break;
            }
            iterator = iterator.next;
        }
    }
}