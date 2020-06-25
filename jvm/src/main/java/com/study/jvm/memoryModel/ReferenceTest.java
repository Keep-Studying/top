/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.memoryModel;

import com.study.jvm.classloader.User;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 引用示例
 *
 * java的引用类型一般分为四种：强引用、软引用、弱引用、虚引用
 *
 * @author study
 * @version : ReferenceTest.java, v 0.1 2020年06月25日 19:29 study Exp $
 */
public class ReferenceTest {
    public static void main(String[] args) {
        //强引用：普通的变量引用
        User user =  new User();

        //软引用：将对象用SoftReference软引用类型的对象包裹，
        //正常情况不会被回收，但是GC做完后发现释放不出空间存放新的对象，则会把这些软引用的对象回收掉。软引用可用来实现内存敏感的高速缓存。
        SoftReference<User> userSoftReference = new SoftReference<>(new User());

        //弱引用：将对象用WeakReference软引用类型的对象包裹，
        // 弱引用跟没引用差不多，GC会直接回收掉，很少用
        WeakReference<User> userWeakReference =new WeakReference<>(new User());

        //虚引用：虚引用也称为幽灵引用或者幻影引用，它是最弱的一种引用关系，几乎不用
    }
}