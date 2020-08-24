/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.atomic;

import com.study.juc.utils.UnsafeInstance;
import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

/**
 * 自己写CAS来更新字段
 * @author study
 * @version : AtomicStudentAgeUpdater.java, v 0.1 2020年08月24日 21:46 study Exp $
 */
public class AtomicStudentAgeUpdater {
    private String name ;
    /**要更新的字段需要用volatile来修饰*/
    private volatile int age;

    public AtomicStudentAgeUpdater(String name,int age){
        this.name = name;
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    /**
     * 结果打印：
     * valueOffset:--->12
     * com.study.juc.atomic.AtomicStudentAgeUpdater object internals:
     *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
     *       0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4                    (object header)                           05 c1 00 20 (00000101 11000001 00000000 00100000)
     *       (536920325)
     *      12     4                int AtomicStudentAgeUpdater.age               18
     *      16     4   java.lang.String AtomicStudentAgeUpdater.name              (object)
     *      20     4                    (loss due to the next object alignment)
     * Instance size: 24 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     *
     * 张三的真实年龄---56
     * */
    public static void main(String[] args) {
        AtomicStudentAgeUpdater updater = new AtomicStudentAgeUpdater("张三",18);

        System.out.println(ClassLayout.parseInstance(updater).toPrintable());

        updater.compareAndSwapAge(18,56);
        System.out.println("张三的真实年龄---"+updater.getAge());
    }

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    private static final long   valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(AtomicStudentAgeUpdater.class.getDeclaredField("age"));
            System.out.println("valueOffset:--->"+valueOffset);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public void compareAndSwapAge(int old,int target){
        unsafe.compareAndSwapInt(this,valueOffset,old,target);
    }
}