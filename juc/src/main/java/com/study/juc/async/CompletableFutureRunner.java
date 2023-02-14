/**
 * Copyright (c) 2021-2023 All Rights Reserved.
 */
package com.study.juc.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureRunner
 *
 * @author Lenovo
 * @version : CompletableFutureRunner.java, v 0.1 2023-02-14 11:23 Lenovo
 */
@Slf4j
public class CompletableFutureRunner {

    @Test
    public void test001(){
        long beginTime = System.currentTimeMillis();
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            log.info(String.format("thread id is %s , start at %s ",Thread.currentThread(),System.currentTimeMillis()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info(String.format("thread id is %s , end at %s ",Thread.currentThread(),System.currentTimeMillis()));
        });
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
             log.info(String.format("thread id is %s , start at %s ",Thread.currentThread(),System.currentTimeMillis()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
             log.info(String.format("thread id is %s , end at %s ",Thread.currentThread(),System.currentTimeMillis()));
        });
        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(() -> {
             log.info(String.format("thread id is %s , start at %s ",Thread.currentThread(),System.currentTimeMillis()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
             log.info(String.format("thread id is %s , end at %s ",Thread.currentThread(),System.currentTimeMillis()));
        });

        CompletableFuture<Void> voidCompletableFuture =
            CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3);
        try {
            Void unused = voidCompletableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
         log.info(String.format("at %s , release time is %s ms",System.currentTimeMillis(),System.currentTimeMillis()- beginTime));
    }


    @Test
    public void test002(){
        long beginTime = System.currentTimeMillis();
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            log.info(String.format("thread id is %s , start at %s ",Thread.currentThread(),System.currentTimeMillis()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info(String.format("thread id is %s , end at %s ",Thread.currentThread(),System.currentTimeMillis()));
        });
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            log.info(String.format("thread id is %s , start at %s ",Thread.currentThread(),System.currentTimeMillis()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info(String.format("thread id is %s , end at %s ",Thread.currentThread(),System.currentTimeMillis()));
        });
        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(() -> {
            log.info(String.format("thread id is %s , start at %s ",Thread.currentThread(),System.currentTimeMillis()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info(String.format("thread id is %s , end at %s ",Thread.currentThread(),System.currentTimeMillis()));
        });

        CompletableFuture<Object> voidCompletableFuture =
            CompletableFuture.anyOf(completableFuture1, completableFuture2, completableFuture3);
        try {
            Object unused = voidCompletableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info(String.format("at %s , release time is %s ms",System.currentTimeMillis(),System.currentTimeMillis()- beginTime));
    }
}