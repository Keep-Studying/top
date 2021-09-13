/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ConnectorTypeEnum
 *
 * @author boyan
 * @version : ConnectorType.java, v 0.1 2021-08-16 15:59 boyan
 */
public enum ConnectorTypeEnum {
    /**
     * mysql
     */
    MYSQL("MYSQL", "mysql connector"),

    /**
     * gauss
     */
    GAUSS("GAUSS", "gauss connector"),

    /**
     * kylin
     */
    KYLIN("KYLIN", "gauss connector"),

    ;
    private String code;
    private String message;


    ConnectorTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private static final Map<String, ConnectorTypeEnum> MAP = new HashMap<>(4);

    static {
        Arrays.stream(values()).forEach(e -> MAP.put(e.getCode(), e));
    }

    /**
     * get by code
     *
     * @param code
     * @return
     */
    public static ConnectorTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        return MAP.get(StringUtils.upperCase(code));
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }
}