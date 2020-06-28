/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.stack;

/**
 * 栈接口
 * @author study
 * @version : MyStack.java, v 0.1 2020年06月28日 0:06 study Exp $
 */
public interface MyStack<E> {

    /**
     * 入栈
     * */
    MyStack<E> push(E e);

    /**
     * 出栈
     * */
    E pop();

    /**
     * 容量
     * */
    int size();

    /**
     * 判空
     * */
    boolean isEmpty();
}