/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.memoryModel;

import com.study.jvm.classloader.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * finalize()方法最终判定对象是否存活
 * 标记的前提是对象在进行可达性分析后发现没有与GC Roots相连接的引用链。
 * 1. 第一次标记并进行一次筛选。
 * 筛选的条件是此对象是否有必要执行finalize()方法。
 * 当对象没有覆盖finalize方法，对象将直接被回收。
 * 2. 第二次标记
 * 如果这个对象覆盖了finalize方法，finalize方法是对象脱逃死亡命运的最后一次机会，
 * 如果对象要在finalize()中成功拯救自己，只要重新与引用链上的任何的一个对象建立关联即可
 *
 * 注意：一个对象的finalize()方法只会被执行一次，也就是说通过调用finalize方法自我救命的机会就一次。
 *
 * @author study
 * @version : OOMTest.java, v 0.1 2020年06月25日 19:42 study Exp $
 */
public class OOMTest {
    public static List<Object> saveSelfList = new ArrayList<>();
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        /**
         *     //User类需要重写finalize方法
         *     @Override
         *     protected void finalize() throws Throwable {
         *         OOMTest.saveSelfList.add(this);
         *         System.out.println("关闭资源，User age=" + age + "即将被回收");
         *     }
         * }
         * */
        while (true){
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }
}