/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.queue;

/**
 * 数组实现的顺序单向队列
 * @author study
 * @version : ArrayQueue.java, v 0.1 2020年06月29日 22:25 study Exp $
 */
public class ArrayQueue<E> implements MyQueue<E>{
    /**
     * 数据
     */
    private Object data[];
    /**
     * 队首下标
     */
    private int    head = 0;
    /**
     * 队尾下标
     */
    private int    tail = 0;
    /**
     * 容量，数组的大小，最大的空间数
     */
    private int    n    = 0;
    /**
     * 当前已经存了几个数了
     */
    private int    size;

    public ArrayQueue(int capacity) {
        this.data = new Object[capacity];
        n = capacity;
    }

    /**
     * 入队 O(1)
     */
    @Override
    public void enqueue(E e) {
        //判断队列是否已满
        if (tail == n) {
            //链表，链表时不能使用CPU缓存的
            //在这里才去移动数组元素，计算时间复杂度：最好，最坏 最好是O(1),最坏的情况下才是O(n);
            // 项目中如何来进行准确的估算呢？平均时间复杂度，n=1000，前999都是O(1) n*2/n,最坏的情况只有一次
            //TODO 移动数组元素
            return;//n-1
        }
        data[tail++] = e;//入队 1 2 3 4 5
    }

    /**
     * 出队 O(1)
     */
    @Override
    public E dequeue() {
        //判断队列是否为空
        if (isEmpty()) {
            //出队列 1 2 3 4 5 后每次移动，这个时间复杂度：O(n),代价高昂
            // 改用在入队的时候如果没有空间了我们在集中移动一次
            //返回null
            return null;
        }
        E e = (E) data[head++];
        return e;
    }

    @Override
    public boolean isEmpty() {
        if (head == tail) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>(10);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

}