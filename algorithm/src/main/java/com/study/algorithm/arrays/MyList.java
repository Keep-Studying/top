/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.arrays;

/**
 * 数组
 * @author study
 * @version : MyList.java, v 0.1 2020年06月27日 16:33 study Exp $
 */
public interface MyList<E> {
    public void add(E e);
    public void remove(int i);
    public void remove(Object e);
    public E get(int i);
    public int size();
    public boolean isEmpty();
}