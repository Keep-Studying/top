/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils;

import com.alibaba.fastjson.JSON;
import com.study.utils.domain.HandlerContext;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author boyan
 * @version : StringUtilTest.java, v 0.1 2021年07月15日 2:11 下午 boyan Exp $
 */
public class StringUtilTest {

    @Test
    public void test01(){
        String basicAuth = DatatypeConverter
                .printBase64Binary(("ADMIN" + ":" + "Aloudata20210520/").getBytes(StandardCharsets.UTF_8));
        System.out.println(basicAuth);
    }

    @Test
    public void test02(){
        System.out.println(StringUtilTest.class.getSimpleName());
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("a");
        HandlerContext handlerContext = new HandlerContext(StringUtilTest.class.getSimpleName(), "test02", "a", 2, objects);

        System.out.println(JSON.toJSONString(handlerContext));
    }

    @Test
    public void test03(){
        String str = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8";
        String s = String.format(str, "127.0.0.1", 3306, "mysql");
        System.out.println(s);
    }
}