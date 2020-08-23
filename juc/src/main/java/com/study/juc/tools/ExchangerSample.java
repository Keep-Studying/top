/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.tools;

import java.util.concurrent.Exchanger;

/**
 * 当一个线程运行到exchange（）方法时会阻塞，另一个线程运行到exchange（）时，
 * 二者交互数据，然后执行后面的程序。
 * 应用场景：极少，了解即可
 * @author study
 * @version : ExchangerSample.java, v 0.1 2020年08月23日 21:09 study Exp $
 */
public class ExchangerSample {
    /**
     * 结果打印：
     * 我是线程：Thread_Thread-0我的数据是：0
     * 我是线程：Thread_Thread-1我的数据是：1
     * 我是线程：Thread_Thread-2我的数据是：2
     * 我是线程：Thread_Thread-3我的数据是：3
     * 我是线程：Thread_Thread-4我的数据是：4
     * 我是线程：Thread_Thread-5我的数据是：5
     * 我是线程：Thread_Thread-6我的数据是：6
     * 我是线程：Thread_Thread-7我的数据是：7
     * 我是线程：Thread_Thread-8我的数据是：8
     * 我是线程：Thread_Thread-9我的数据是：9
     * 我是线程：Thread_Thread-0我原先的数据为：0 , 交换后的数据为：1
     * 我是线程：Thread_Thread-3我原先的数据为：3 , 交换后的数据为：2
     * 我是线程：Thread_Thread-1我原先的数据为：1 , 交换后的数据为：0
     * 我是线程：Thread_Thread-4我原先的数据为：4 , 交换后的数据为：5
     * 我是线程：Thread_Thread-2我原先的数据为：2 , 交换后的数据为：3
     * 我是线程：Thread_Thread-5我原先的数据为：5 , 交换后的数据为：4
     * 我是线程：Thread_Thread-7我原先的数据为：7 , 交换后的数据为：6
     * 我是线程：Thread_Thread-6我原先的数据为：6 , 交换后的数据为：7
     * 我是线程：Thread_Thread-9我原先的数据为：9 , 交换后的数据为：8
     * 我是线程：Thread_Thread-8我原先的数据为：8 , 交换后的数据为：9
     * */
    public static void main(String[] args) {
        final Exchanger<Integer> exchanger = new Exchanger<>();
        for (int i = 0; i < 10; i++) {
            final Integer num = i;
            new Thread(){
                public void run(){
                    System.out.println("我是线程：Thread_" + this.getName() + "我的数据是：" + num);
                    try {
                        Integer exchangeNum = exchanger.exchange(num);
                        Thread.sleep(1000);
                        System.out.println("我是线程：Thread_" + this.getName() + "我原先的数据为：" + num + " , 交换后的数据为：" + exchangeNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}