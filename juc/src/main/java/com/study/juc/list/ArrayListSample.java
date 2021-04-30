/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @author study
 * @version : ArrayListSample.java, v 0.1 2020年08月31日 23:48 study Exp $
 */
@Slf4j
public class ArrayListSample {
    /**
     * Exception in thread "Thread-1" java.lang.UnsupportedOperationException
     * 	at java.util.AbstractList.add(AbstractList.java:148)
     * 	at java.util.AbstractList.add(AbstractList.java:108)
     * 	at com.study.juc.list.ArrayListSample.lambda$main$1(ArrayListSample.java:29)
     * 	at java.lang.Thread.run(Thread.java:748)
     * 00:04:02.498 [Thread-0] INFO com.study.juc.list.ArrayListSample - 1
     * 00:04:02.505 [Thread-0] INFO com.study.juc.list.ArrayListSample - 2
     * 00:04:02.506 [Thread-0] INFO com.study.juc.list.ArrayListSample - 3
     * 00:04:02.506 [Thread-0] INFO com.study.juc.list.ArrayListSample - 4
     * 00:04:03.490 [main] INFO com.study.juc.list.ArrayListSample - 第1个元素为：1
     * 00:04:03.490 [main] INFO com.study.juc.list.ArrayListSample - 第2个元素为：2
     * 00:04:03.490 [main] INFO com.study.juc.list.ArrayListSample - 第3个元素为：3
     * 00:04:03.490 [main] INFO com.study.juc.list.ArrayListSample - 第4个元素为：4
     * */
    public static void main(String[] args) throws InterruptedException {

        final List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4});

        // 线程A读list集合
        new Thread(() -> {
            for (int i = 0; i < list.size() ; i++) {
                log.info(list.get(i).toString());
            }
        }).start();

        new Thread(() -> {
            list.add(5);
            list.add(6);
        }).start();

        Thread.sleep(1000);

        // 打印所有结果
        for (int i = 0; i < list.size(); i++) {
            log.info("第" + (i + 1) + "个元素为：" + list.get(i));
        }
    }
    @Test
    public void test(){
        /*int data[] = {1,5,0,10};
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));*/


        ArrayList<Long> old = new ArrayList<>();
        old.add(new Long(7));
        old.add(new Long(9));
        old.add(new Long(2));
        old.add(new Long(0));
        old.add(new Long(5));

        /*LinkedList<Long> longs1 = new LinkedList<>();
        longs1.*/

        ArrayList<Long> longs = new ArrayList<>();
        longs.add(new Long(9));
        longs.add(new Long(5));
        longs.add(new Long(2));
        longs.sort((k1,k2)->{
            return old.indexOf(k1) - old.indexOf(k2);
        });

        System.out.println(longs.toString());
    }
}