/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;

/**
 * Json类型数据处理工具类
 * @author study
 * @version : JsonUtil.java, v 0.1 2021年07月04日 8:42 下午 study Exp $
 */
public class JsonUtil {
    private JsonUtil() {
        throw new IllegalStateException("Class JsonUtil is an utility class !");
    }

    /**
     * 把Object转换成Json字符串
     * @param object
     * @param features
     * @return
     */
    public static String toJSONString(Object object, SerializerFeature... features){
        return JSON.toJSONString(object,features);
    }

    /**
     * 将Json串转换成clazz对象
     * @param json
     * @param clazz
     * @param features
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz, Feature... features){
        return (T)JSON.parseObject(json, clazz,features);
    }

    /**
     * 将Json串转换成TypeReference的<T>对象
     * @param json
     * @param type
     * @param features
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, TypeReference<T> type, Feature... features){
        return (T)JSON.parseObject(json, type,features);
    }

    /**
     *  将Json串转换成HashMap<String, Object>对象
     * @param json
     * @return
     */
    public static HashMap<String, Object> parseObjectAsMap(String json){
        TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {};
        return JSON.parseObject(json, typeReference);
    }

    /**
     * 将Json串转换成HashMap<String, String>对象
     * @param json
     * @return
     */
    public static HashMap<String, String> parseObjectAsStringMap(String json){
        TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String, String>>() {};
        return JSON.parseObject(json, typeReference);
    }

    /**
     * 将Json串转为UniformEvent对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    /*public static  <T> UniformEvent<T> parseUniform(String json, Class<?> clazz) {
        *//* 此时T未知类型，不能直接使用无参，会导致T类型变成JSONObject *//*
        UniformEvent<T> uniformEvent = JSON.parseObject(json, new TypeReference<UniformEvent<T>>(clazz) {});
        return uniformEvent;
    }*/
}