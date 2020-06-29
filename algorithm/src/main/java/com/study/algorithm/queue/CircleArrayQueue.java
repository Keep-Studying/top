/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.queue;

/**
 * 数组实现的循环队列
 * 实现有点问题，后续调整，放入满额的元素时，判满和判空方法会有问题
 * @author study
 * @version : CircleArrayQueue.java, v 0.1 2020年06月30日 0:05 study Exp $
 */
public class CircleArrayQueue<E> implements MyQueue<E>{
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

    public CircleArrayQueue(int capacity) {
        this.data = new Object[capacity];
        this.n = capacity;
    }

    @Override
    public void enqueue(E e) {
        //判断队列已满
        if(isFull()){
            return;
        }
        data[tail] = e; //1 2 3 4 5  排序，树形结构
        tail = (tail + 1) % n; //循环队列 tail=7 8越界了，(7+1)%8==0
        size++;
        /*System.out.println(tail);
        System.out.println(JSON.toJSONString(data));*/
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            return null;
        }
        E e =(E)data[head];
        head = (head + 1) % n;
        //System.out.println(JSON.toJSONString(data));
        return e;
    }

    @Override
    public boolean isEmpty() {
        //要判断空，怎么判断空？
        if(tail == head){
            //System.out.println(head+"  "+tail);
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return size == n;
/*        if((tail + 1) % n == head){
            return true;
        }
        return false;*/
    }

    public static void main(String[] args) {
        CircleArrayQueue<Integer> queue = new CircleArrayQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println(queue.isFull());

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        System.out.println(queue.isEmpty());
    }
}