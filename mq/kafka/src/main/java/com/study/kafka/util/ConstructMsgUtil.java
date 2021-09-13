/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.kafka.util;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.study.kafka.domain.CommonConstants.LOG_DEFAULT;
import static com.study.kafka.domain.CommonConstants.LOG_SEP_COMMA;

/**
 * ConstructMsgUtil
 *
 * @author boyan
 * @version : ConstructMsgUtil.java, v 0.1 2021-09-01 00:21 boyan
 */
public class ConstructMsgUtil {

    public static String constructMsg(Object object) {

        if (object == null) {
            return LOG_DEFAULT;
        } else if (object.getClass() == String.class || object.getClass() == Integer.class
            || object.getClass() == Long.class || object.getClass() == Double.class) {

            //基本类型直接输出
            return object.toString();
        } else if (object.getClass() == BigDecimal.class) {

            return String.valueOf(((BigDecimal) object).doubleValue());
        } else if (object instanceof List<?>) {

            //如果是List的参数，将各条记录信息打印出来
            return constructListMsg(object);
        } else {

            return JsonUtil.toJsonString(object);
        }
    }

    /**
     * 获取List类型的参数
     *
     * @param object
     * @return
     */
    private static String constructListMsg(Object object) {
        List<?> list = (List<?>) object;

        if (CollectionUtils.isEmpty(list)) {
            return LOG_DEFAULT;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getClass() == String.class) {
                sb.append(list.get(i));
            } else {
                sb.append(JsonUtil.toJsonString(list.get(i)));
            }

            sb.append(LOG_SEP_COMMA);
        }

        return sb.toString();
    }
}