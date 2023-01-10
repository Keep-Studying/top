/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.juc.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * BlockingQueueSample
 *
 * @author boyan
 * @version : BlockingQueueSample.java, v 0.1 2023-01-04 18:13 boyan
 */
public class BlockingQueueSample {

    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);
        Thread producerThread = new Thread(new NumProducer(blockingQueue));
        Thread consumerThread = new Thread(new NumConsumer(blockingQueue));
        producerThread.start();
        consumerThread.start();
    }
}


@Slf4j
class NumProducer implements Runnable{
    private BlockingQueue<Integer> blockingQueue;
    public NumProducer(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try{
            while(true){
                int num = ThreadLocalRandom.current().nextInt(100);
                blockingQueue.put(num);
//                log.info("NumProducer process the number {}",num);
                System.out.println(String.format("NumProducer process the number %s",num));
            }
        }catch(Exception e){
            // 错误日志落盘
//            log.error("NumProducer occur error {} ",e);
            e.printStackTrace();
        }

    }
}

@Slf4j
class NumConsumer implements Runnable{
    private BlockingQueue<Integer> blockingQueue;
    public NumConsumer(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try{
            while(true){
                Integer num = blockingQueue.take();
//                log.info("NumConsumer process the number {}",num);
                System.out.println(String.format("NumConsumer process the number %s",num));
            }
        }catch(Exception e){
            // 错误日志落盘
//            log.error("NumConsumer occur error {} ",e);
            e.printStackTrace();
        }

    }
}