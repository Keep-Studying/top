/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.mesi;

import sun.misc.Contended;

/**
 *
 * 什么是伪共享？
 * CPU缓存系统中多是以缓存行（cache line）为单位存储的。目前主流的
 * CPU cache的cache line大小都是64byte。在多线程情况下，如果需要
 * 修改“共享同一个缓存行的变量”，就会无意中影响彼此的性能，这就是
 * 伪共享（FALSE Sharing）。
 *
 * 伪共享解决方案：
 * 类添加sun.misc.Contended，
 * 同时JVM启动参数添加-XX:-RestrictContended
 *
 * @author study
 * @version : VolatileLong.java, v 0.1 2020年08月11日 0:53 study Exp $
 */
@Contended
public class VolatileLong {
    public volatile long value = 0L;
    //public long p1, p2, p3, p4, p5, p6;
}