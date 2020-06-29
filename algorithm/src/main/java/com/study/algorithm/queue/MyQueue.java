/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.queue;

/**
 * 队列接口
 * @author study
 * @version : MyQueue.java, v 0.1 2020年06月30日 0:05 study Exp $
 */
public interface MyQueue<E> {
    /**
     * 入队
     */
    public void enqueue(E e);

    /**
     * 出队
     */
    public E dequeue();

    /**
     * 队列是否为空
     */
    public boolean isEmpty();

    /**
     * 队列是否已满
     * */
    public boolean isFull();
}