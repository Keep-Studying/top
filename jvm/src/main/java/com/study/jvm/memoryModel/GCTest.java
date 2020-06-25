/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.jvm.memoryModel;

/**
 * GC测试（受电脑配置影响）
 *
 * 添加JVM运行参数： -XX:+PrintGCDetails
 *
 * 注释掉allocation2和allocation3 运行结果：
 * Heap
 *  PSYoungGen      total 76288K, used 65536K [0x000000076b400000, 0x0000000770900000, 0x00000007c0000000)
 *   eden space 65536K, 100% used [0x000000076b400000,0x000000076f400000,0x000000076f400000)
 *   from space 10752K, 0% used [0x000000076fe80000,0x000000076fe80000,0x0000000770900000)
 *   to   space 10752K, 0% used [0x000000076f400000,0x000000076f400000,0x000000076fe80000)
 *  ParOldGen       total 175104K, used 0K [0x00000006c1c00000, 0x00000006cc700000, 0x000000076b400000)
 *   object space 175104K, 0% used [0x00000006c1c00000,0x00000006c1c00000,0x00000006cc700000)
 *  Metaspace       used 3342K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
 *
 * 注释掉allocation3 运行结果：
 * [GC (Allocation Failure) [PSYoungGen: 65253K->936K(76288K)] 65253K->60944K(251392K), 0.0279083 secs] [Times: user=0.13 sys=0.02,
 * real=0.03 secs]
 * Heap
 *  PSYoungGen      total 76288K, used 9591K [0x000000076b400000, 0x0000000774900000, 0x00000007c0000000)
 *   eden space 65536K, 13% used [0x000000076b400000,0x000000076bc73ef8,0x000000076f400000)
 *   from space 10752K, 8% used [0x000000076f400000,0x000000076f4ea020,0x000000076fe80000)
 *   to   space 10752K, 0% used [0x0000000773e80000,0x0000000773e80000,0x0000000774900000)
 *  ParOldGen       total 175104K, used 60008K [0x00000006c1c00000, 0x00000006cc700000, 0x000000076b400000)
 *   object space 175104K, 34% used [0x00000006c1c00000,0x00000006c569a010,0x00000006cc700000)
 *  Metaspace       used 3342K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
 *
 *  不注释任何内容时，运行结果：
 * [GC (Allocation Failure) [PSYoungGen: 65253K->952K(76288K)] 65253K->60960K(251392K), 0.0311467 secs] [Times: user=0.08 sys=0.02,
 *  real=0.03 secs]
 * Heap
 *  PSYoungGen      total 76288K, used 13878K [0x000000076b400000, 0x0000000774900000, 0x00000007c0000000)
 *   eden space 65536K, 19% used [0x000000076b400000,0x000000076c09fb68,0x000000076f400000)
 *   from space 10752K, 8% used [0x000000076f400000,0x000000076f4ee030,0x000000076fe80000)
 *   to   space 10752K, 0% used [0x0000000773e80000,0x0000000773e80000,0x0000000774900000)
 *  ParOldGen       total 175104K, used 60008K [0x00000006c1c00000, 0x00000006cc700000, 0x000000076b400000)
 *   object space 175104K, 34% used [0x00000006c1c00000,0x00000006c569a010,0x00000006cc700000)
 *  Metaspace       used 3343K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
 *  
 * @author study
 * @version : GCTest.java, v 0.1 2020年06月25日 15:30 study Exp $
 */
public class GCTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
        allocation1 = new byte[30000*1024];
        //allocation2 = new byte[8000*1024];

        //allocation3 = new byte[1000*1024];
        allocation4 = new byte[1000*1024];
        allocation5 = new byte[1000*1024];
        allocation6 = new byte[1000*1024];
    }
}