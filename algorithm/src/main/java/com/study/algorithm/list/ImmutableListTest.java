/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.algorithm.list;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

/**
 * ImmutableListTest
 *
 * @author Lenovo
 * @version : ImmutableListTest.java, v 0.1 2023-02-14 11:13 Lenovo
 */
public class ImmutableListTest {


    @Test
    public void test001(){
        ImmutableList<String> strings = ImmutableList.of("a", "b", "c");
        System.out.println(strings.toString());
    }
}