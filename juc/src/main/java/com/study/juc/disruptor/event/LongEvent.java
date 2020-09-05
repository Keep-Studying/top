/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.disruptor.event;

/**
 * @author study
 * @version : LongEvent.java, v 0.1 2020年09月05日 0:21 study Exp $
 */
public class LongEvent {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}