/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.HashMap;
import java.util.HashSet;

/**
 * JsonUtil
 *
 * @author boyan
 * @version : JsonUtil.java, v 0.1 2021-08-31 14:38 boyan
 */
public class JsonUtil {
    private static SimplePropertyPreFilter filter;

    static {
        filter = new SimplePropertyPreFilter();
        //排除的字段
        HashSet<String> excludeSet = new HashSet<>();
        filter.getExcludes().addAll(excludeSet);
    }

    private JsonUtil() {
        throw new IllegalStateException("Class JsonUtil is an utility class !");
    }

    /**
     * 把Object转换成Json字符串(默认带有filter)
     *
     * @param object
     * @param features
     * @return
     */
    public static String toJsonString(Object object, SerializerFeature... features) {
        return JSON.toJSONString(object, filter, features);
    }

    /**
     * 将Json串转换成clazz对象
     *
     * @param json
     * @param clazz
     * @param features
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz, Feature... features) {
        return (T) JSON.parseObject(json, clazz, features);
    }

    /**
     * 将Json串转换成TypeReference的<T>对象
     *
     * @param json
     * @param type
     * @param features
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, TypeReference<T> type, Feature... features) {
        return (T) JSON.parseObject(json, type, features);
    }

    /**
     * 将Json串转换成HashMap<String, Object>对象
     *
     * @param json
     * @return
     */
    public static HashMap<String, Object> parseObjectAsMap(String json) {
        TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {};
        return JSON.parseObject(json, typeReference);
    }

    /**
     * 将Json串转换成HashMap<String, String>对象
     *
     * @param json
     * @return
     */
    public static HashMap<String, String> parseObjectAsStringMap(String json) {
        TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String, String>>() {};
        return JSON.parseObject(json, typeReference);
    }
}