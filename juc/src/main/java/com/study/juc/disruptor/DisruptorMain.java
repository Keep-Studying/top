/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.study.juc.disruptor.event.LongEvent;
import com.study.juc.disruptor.event.LongEventFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author study
 * @version : DisruptorMain.java, v 0.1 2020年09月05日 0:20 study Exp $
 */
public class DisruptorMain {
    public static void main(String[] args) {
        // 1.创建一个可缓存的线程 提供线程来出发Consumer 的事件处理
        ExecutorService executor = Executors.newCachedThreadPool();
        // 2.创建工厂
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        // 3.创建ringBufferSize 大小
        int ringBufferSize = 1024;
        // 4.创建Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize,
                executor, ProducerType.SINGLE,new YieldingWaitStrategy());
        // 5.连接消费端方法
        disruptor.handleEventsWith(new LongEventHandler(1),new LongEventHandler(2));
        // 6.启动
        disruptor.start();
        // 7.创建RingBuffer容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 8.创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        // 9.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i <= 100; i++) {
            byteBuffer.putLong(0,i);
            producer.onData(byteBuffer);
        }
        //10.关闭disruptor和executor
        disruptor.shutdown();
        executor.shutdown();
    }
}