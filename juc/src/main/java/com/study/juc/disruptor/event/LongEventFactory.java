/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * @author study
 * @version : LongEventFactory.java, v 0.1 2020年09月05日 0:21 study Exp $
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}