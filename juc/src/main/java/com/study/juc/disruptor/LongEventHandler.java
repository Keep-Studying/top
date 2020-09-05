/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.disruptor;

import com.lmax.disruptor.EventHandler;
import com.study.juc.disruptor.event.LongEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 消费者
 * @author study
 * @version : LongEventHandler.java, v 0.1 2020年09月05日 0:22 study Exp $
 */
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> {
    private long serial = 0;

    public LongEventHandler(long serial) {
        this.serial = serial;
    }

    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        log.info("消费者-{}:{}",this.serial,event.getValue());
    }
}