/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.ext.testbeanfacotoryPostProcessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * @author study
 * @version : Log.java, v 0.1 2020年08月13日 11:34 study Exp $
 */
@Component
public class Log {

    @Value("1")
    private int flag;

    @Override
    public String toString() {
        return new StringJoiner(", ", Log.class.getSimpleName() + "[", "]")
                .add("flag=" + flag)
                .toString();
    }

    public Log() {
        System.out.println("我是Log的构造方法");
    }

    /**
     * Getter method for property <tt>flag</tt>.
     *
     * @return property value of flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Setter method for property <tt>flag</tt>.
     *
     * @param flag value to be assigned to property flag
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }
}