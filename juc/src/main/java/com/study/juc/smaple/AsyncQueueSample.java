/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.juc.smaple;

import com.study.juc.misc.Loggers;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version : AsyncQueueSample.java, v 0.1 2021年04月19日 22:45 study Exp $
 */
public class AsyncQueueSample {

    ThreadPoolExecutor threadPoolExecutor;

    private Notifier notifier;

    @PostConstruct
    public void init(){
        this.notifier = new Notifier();
        this.threadPoolExecutor = new ThreadPoolExecutor(5,10,5000,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        threadPoolExecutor.submit(notifier);
    }

    public void put(Object command){
        notifier.addTask(command);
    }

    public boolean isEmpty(){
        return notifier.isEmpty();
    }

    class Notifier implements Runnable{
        private BlockingQueue<Object> tasks = new ArrayBlockingQueue<>(64);


        /**
         * Add new notify task to queue.
         *
         */
        public void addTask(Object task) {
            tasks.offer(task);
        }

        public boolean isEmpty(){
            return tasks.isEmpty();
        }

        @Override
        public void run() {

            Loggers.DISTRO.info("distro notifier started");
            for(;;){
                try {
                    Object command = tasks.take();
                    handle(command);
                } catch (Throwable e) {
                    Loggers.DISTRO.error("[NACOS-DISTRO] Error while handling notifying task", e);
                }
            }
        }

        private void handle(Object command) {
            try {
                System.out.println(Thread.currentThread()+":"+command.toString());
            } catch (Throwable e) {
                Loggers.DISTRO.error("[NACOS-DISTRO] Error while handling notifying task", e);
            }
        }
    }
}