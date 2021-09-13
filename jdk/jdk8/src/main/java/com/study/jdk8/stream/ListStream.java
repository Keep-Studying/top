/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.jdk8.stream;

import com.study.jdk8.domain.DataSource;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ListStream
 *
 * @author boyan
 * @version : ListStream.java, v 0.1 2021-08-19 12:00 boyan
 */
public class ListStream {

    private static List<DataSource> dataSources = new ArrayList<>();;

    static {
        dataSources.add(new DataSource().setGuid("1").setUrl("url1"));
        dataSources.add(new DataSource().setGuid("2").setUrl("url2"));
        dataSources.add(new DataSource().setGuid("3").setUrl("url3"));
        dataSources.add(new DataSource().setGuid("3").setUrl("url4"));
    }

    @Test
    public void test01(){
        List<DataSource> collect = dataSources.stream().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test02(){
        Set<String> collect = dataSources.stream().map(x -> x.getGuid()).collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void test03(){
        Map<String, DataSource> collect = dataSources.stream().collect(Collectors.toMap(x -> x.getGuid(), x -> x, (x, y) -> x));
        System.out.println(collect);

//        Map<String, DataSource> collect1 = dataSources.stream().collect(Collectors.toMap(x -> x.getGuid(), x -> x));
//        System.out.println(collect1);
    }

    @Test
    public void test04(){
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.stream().forEach(item-> System.out.println(item));
        List<DataSource> collect = dataSources.stream().filter(x -> !set.contains(x.getGuid())).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test05(){
        ArrayList<String> strings = new ArrayList<>();
        strings.stream().forEach(s-> System.out.println(s));
    }
}