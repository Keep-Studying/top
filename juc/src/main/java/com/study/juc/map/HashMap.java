/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.map;

/**
 * @version  jdk7 hashMap
 *
 * {@Code 构造函数
 *     public HashMap(int initialCapacity) {
 *         this(initialCapacity, DEFAULT_LOAD_FACTOR);
 *     }
 *
 *     public HashMap(int initialCapacity, float loadFactor) {
 *         if (initialCapacity < 0)
 *             throw new IllegalArgumentException("Illegal initial capacity: " +
 *                                                initialCapacity);
 *         if (initialCapacity > MAXIMUM_CAPACITY)
 *             initialCapacity = MAXIMUM_CAPACITY;
 *         if (loadFactor <= 0 || Float.isNaN(loadFactor))
 *             throw new IllegalArgumentException("Illegal load factor: " +
 *                                                loadFactor);
 *
 *         this.loadFactor = loadFactor;
 *         threshold = initialCapacity;
 *         init();
 *     }
 * }
 * {@Code get(k)
 *     public V get(Object key) {
 *         if (key == null)
 *             return getForNullKey();
 *         Entry<K,V> entry = getEntry(key);
 *
 *         return null == entry ? null : entry.getValue();
 *     }
 *
 *     final Entry<K,V> getEntry(Object key) {
 *         if (size == 0) {
 *             return null;
 *         }
 *
 *         int hash = (key == null) ? 0 : hash(key);
 *         for (Entry<K,V> e = table[indexFor(hash, table.length)];
 *              e != null;
 *              e = e.next) {
 *             Object k;
 *             if (e.hash == hash &&
 *                 ((k = e.key) == key || (key != null && key.equals(k))))
 *                 return e;
 *         }
 *         return null;
 *     }
 *
 *     //Returns index for hash code h.
 *     static int indexFor(int h, int length) {
 *         // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
 *         return h & (length-1);
 *     }
 * }
 * {@Code put(k,v)
 *     public V put(K key, V value) {
 *         if (table == EMPTY_TABLE) {
 *             inflateTable(threshold);
 *         }
 *         if (key == null)
 *             return putForNullKey(value);
 *         int hash = hash(key);
 *         int i = indexFor(hash, table.length);
 *         for (Entry<K,V> e = table[i]; e != null; e = e.next) {
 *             Object k;
 *             if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
 *                 V oldValue = e.value;
 *                 e.value = value;
 *                 e.recordAccess(this);
 *                 return oldValue;
 *             }
 *         }
 *
 *         modCount++;
 *         addEntry(hash, key, value, i);
 *         return null;
 *     }
 *
 *     //Inflates the table.
 *     private void inflateTable(int toSize) {
 *         // Find a power of 2 >= toSize
 *         int capacity = roundUpToPowerOf2(toSize);
 *
 *         threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
 *         table = new Entry[capacity];
 *         initHashSeedAsNeeded(capacity);
 *     }
 *
 *     void addEntry(int hash, K key, V value, int bucketIndex) {
 *         if ((size >= threshold) && (null != table[bucketIndex])) {
 *             resize(2 * table.length);
 *             hash = (null != key) ? hash(key) : 0;
 *             bucketIndex = indexFor(hash, table.length);
 *         }
 *
 *         createEntry(hash, key, value, bucketIndex);
 *     }
 *
 *    //扩容，容量是原有容量的2倍
 *    void resize(int newCapacity) {
 *         Entry[] oldTable = table;
 *         int oldCapacity = oldTable.length;
 *         if (oldCapacity == MAXIMUM_CAPACITY) {
 *             threshold = Integer.MAX_VALUE;
 *             return;
 *         }
 *
 *         Entry[] newTable = new Entry[newCapacity];
 *         transfer(newTable, initHashSeedAsNeeded(newCapacity));
 *         table = newTable;
 *         threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
 *     }
 *
 *     //Transfers all entries from current table to newTable.
 *     //该方法在多线程环境下，有可能出现链表环；但线程环境下是没有问题的
 *     void transfer(Entry[] newTable, boolean rehash) {
 *         int newCapacity = newTable.length;
 *         for (Entry<K,V> e : table) {
 *             while(null != e) {
 *                 Entry<K,V> next = e.next;//记录old hash表中e.next
 *                 if (rehash) {
 *                     e.hash = null == e.key ? 0 : hash(e.key);
 *                 }
 *                 //rehash计算出数组的位置（hash表中桶的位置）
 *                 int i = indexFor(e.hash, newCapacity);
 *                 e.next = newTable[i];//e要插入链表的头部，所以要先将e.next指向new hash表中的第一个元素
 *                 newTable[i] = e;//将e放入到new hash表的头部
 *                 e = next;//转移e到下一个节点，继续循环下去
 *             }
 *         }
 *     }
 * }
 * @author study
 * @version : HashMap.java, v 0.1 2020年08月30日 10:42 study Exp $
 */
public class HashMap {
}