/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ListTest
 *
 * @author boyan
 * @version : ListTest.java, v 0.1 2021-08-20 10:00 boyan
 */
public class ListTest {

    @Test
    public void test01(){
        List<String> strings = new ArrayList<>();
        System.out.println(CollectionUtils.isEmpty(strings));
        strings.add("1");
        strings.add("2");
        strings.add("3");

        System.out.println(JSON.toJSONString(strings));
        strings.removeAll(strings);
        System.out.println(strings.size());
        System.out.println(JSON.toJSONString(strings));
    }
}