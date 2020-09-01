/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.set;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author study
 * @version : ConcurrentHashSetRunner.java, v 0.1 2020年09月01日 8:22 study Exp $
 */
public class ConcurrentHashSetRunner {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet();
        Integer nextInt = new Random().nextInt();
        copyOnWriteArraySet.add(nextInt);

        System.out.println(copyOnWriteArraySet.contains(nextInt));
    }
}