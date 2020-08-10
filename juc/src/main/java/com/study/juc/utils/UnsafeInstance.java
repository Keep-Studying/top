/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe获取工具类
 * @author study
 * @version : UnsafeInstance.java, v 0.1 2020年08月11日 0:30 study Exp $
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}