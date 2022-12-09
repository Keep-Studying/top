/**
 * Copyright (c) 2021-2022 All Rights Reserved.
 */
package com.study.algorithm.list;

import java.util.HashMap;
import java.util.Map;

/**
 * LruCache
 * 请你设计并实现一个满LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
 * 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 题解链接：
 * https://leetcode.cn/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 *
 * @author boyan
 * @version : LruCache.java, v 0.1 2022-12-08 11:56 boyan
 */
public class LruCache {

    class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode() {
        }

        public LinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, LinkedNode> cache = new HashMap<>();

    /**
     * 链表的头尾结点
     */
    private LinkedNode head, tail;
    private int size;
    private int capacity;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 头插法插入结点
     * @param node
     */
    private void addToHead(LinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 将结点从链表中移除
     * @param node
     */
    private void removeNode(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将结点移动到头部
     * @param node
     */
    private void removeToHead(LinkedNode node) {
        this.removeNode(node);
        this.addToHead(node);
    }

    /**
     * 移除尾部结点
     */
    private LinkedNode removeTail() {
        LinkedNode prev = tail.prev;
        this.removeNode(prev);
        return prev;
    }

    /**
     * get 方法
     * @param key
     * @return
     */
    public int get(int key) {
        LinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        this.removeToHead(node);
        return node.value;
    }

    /**
     * put 方法
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        LinkedNode node = cache.get(key);
        if (node == null) {
            LinkedNode newNode = new LinkedNode(key,value);
            // 放到cache中
            this.cache.put(key, newNode);
            // 添加至双向链表的头部
            this.addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量限制了，则移除尾部结点
                LinkedNode removeTail = this.removeTail();
                this.cache.remove(removeTail.key);
                --size;
            }
        } else {
            //结点存在，则更新
            node.value = value;
            //结点被访问过，则移动到头部
            this.removeToHead(node);
        }
    }
}
