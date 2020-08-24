/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * AtomicReference：原子更新引用类型
 * @author study
 * @version : AtomicReferenceArrayRunner.java, v 0.1 2020年08月24日 21:27 study Exp $
 */
public class AtomicReferenceArrayRunner {
    static Sample[] oldValue = new Sample[]{new Sample(1),new Sample(2)};
    /**
     * 是原array的拷贝副本
     * {@Code
     * public AtomicReferenceArray(E[] array) {
     *         // Visibility guaranteed by final field guarantees
     *         this.array = Arrays.copyOf(array, array.length, Object[].class);
     *     }
     * }
     * */
    static AtomicReferenceArray<Sample> objarray = new AtomicReferenceArray(oldValue);
    public static void main(String[] args) {
        System.out.println(objarray.get(0).getSequence());

        objarray.set(0,new Sample(3));

        System.out.println(objarray.get(0).getSequence());
        System.out.println(oldValue[0].getSequence());

    }
}

@Data
class Sample{
    private Integer sequence;

    public Sample(Integer seq){
        sequence = seq;
    }
}