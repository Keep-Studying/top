/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.pool;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 线程池ThreadPoolExecutor
 * @author study
 * @version : ThreadPool.java, v 0.1 2020年09月01日 23:39 study Exp $
 */
public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*for (int i=0;i<300;i++){
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            }).start();
        }*/
        //阿里巴巴开发手册上明确不推荐使用Executors创建线程池
        //Executors.newCachedThreadPool();
        /**
         * 线程数量设置经验
         * CPU密集型：CPU核数+1
         * IO密集型：2*CPU核数+1
         * RocketMQ，Eureka，Nacos等中间件，大多都是用的是：2*CPU核数
         * 最佳线程数=CPU核数*[1+(IO耗时/CPU耗时)]
         * */
        /**
         * 输出内容如下：
         * submit method,i am task ：pool-1-thread-1
         * 0
         * submit method,i am task ：pool-1-thread-1
         * submit method,i am task ：pool-1-thread-2
         * 1
         * submit method,i am task ：pool-1-thread-3
         * 2
         * submit method,i am task ：pool-1-thread-4
         * 3
         * submit method,i am task ：pool-1-thread-5
         * 4
         * 5
         * */
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5,10,5000,
                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            //execute是无返回值的方式
            /*threadPoolExecutor.execute(()->{
                System.out.println("execute method,i am task ："+Thread.currentThread().getName());
            });*/
            //submit是有返回值的
            futures.add(threadPoolExecutor.submit(()->{
                System.out.println("submit method,i am task ："+Thread.currentThread().getName());
            },i));
        }
        for (Future future : futures) {
            System.out.println(future.get());
        }

        threadPoolExecutor.shutdown();  //running->shutdown
        threadPoolExecutor.shutdownNow(); //running->stop
    }
}