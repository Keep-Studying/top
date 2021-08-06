/**
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 *
 * @author boyan
 * @version : JacksonUtils.java, v 0.1 2021年07月11日 10:37 上午 boyan Exp $
 */
public class JacksonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }



    public static <T> T readValue(String json, TypeReference<T> valueTypeRef)throws IOException {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json to object fail:" + json + " type:" + valueTypeRef.getType().getTypeName(), e);
        }
    }

    public static String toString(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("to json string fail:" + o, e);
        }
    }
}