/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater：原子更新整型的字段的更新器
 * 原子更新字段类都是抽象类，每次使用时都必须使用静态方法newUpdater创建一个更新器。
 *
 * 原子更新类的字段必须使用public volatile修饰符
 * 假如不是volatile，会抛出如下错误；如不是public，会抛出其他错误
 * {@Code
 * Caused by: java.lang.IllegalArgumentException: Must be volatile type
 * 	at java.util.concurrent.atomic.AtomicIntegerFieldUpdater$AtomicIntegerFieldUpdaterImpl.<init>(AtomicIntegerFieldUpdater.java:412)
 * 	at java.util.concurrent.atomic.AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdater.java:88)
 * 	at com.study.juc.atomic.AtomicIntegerFieldUpdateRunner.<clinit>(AtomicIntegerFieldUpdateRunner.java:15)
 * Exception in thread "main"
 * }
 * @author study
 * @version : AtomicIntegerFieldUpdateRunner.java, v 0.1 2020年08月24日 21:19 study Exp $
 */
public class AtomicIntegerFieldUpdateRunner {
    static AtomicIntegerFieldUpdater aifu = AtomicIntegerFieldUpdater.newUpdater(Student.class,"old");

    public static void main(String[] args) {
        Student stu = new Student("张三",18);
        System.out.println(aifu.getAndIncrement(stu));
        System.out.println(aifu.getAndIncrement(stu));
        System.out.println(aifu.get(stu));
    }

    static class Student{
        private String name;
        public    int old;

        public Student(String name ,int old){
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}