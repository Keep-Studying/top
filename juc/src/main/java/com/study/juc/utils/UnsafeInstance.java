/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.StampedLock;

/**
 * Unsafe获取工具类
 * {@Code 1.内存操作
 *  //分配内存，相当于C++的malloc函数
 * public native long allocateMemory(long bytes);
 *
 * //扩充内存
 * public native long reallocateMemory(long address, long bytes);
 *
 * //释放内存
 * public native void freeMemory(long address);
 *
 * //在给定的内存块中设置值
 * public native void setMemory(Object o, long offset, long bytes, byte value);
 *
 * //内存拷贝
 * public native void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes);
 *
 * //获取给定地址值，忽略修饰限定符的访问限制，与此类似操作还有：getInt，getDouble，getLong，getChar等
 * public native Object getObject(Object o, long offset);
 *
 * //为给定地址设置值，忽略修饰符限定符的访问限制，与此类似操作还有：putInt，putDouble，putLong，putChar
 * public native void putObject(Object o, long offset, Object x);
 *
 * public native byte getByte(long var1);
 *
 * //为给定地址设置byte类型的值（当且仅当该内存地址为allocateMemory分配时，此方法结果才是确定的）
 * public native void putByte(long address, byte x);
 * }
 *
 * {@Code 2.CAS相关
 *
 * /**
 *  * CAS
 *  * @param o         包含要修改field的对象
 *  * @param offset    对象中某field的偏移量
 *  * @param expected  期望值
 *  * @param update    更新值
 *  * @return          true | false
 *  * /
 * public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);
 *
 * public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
 *
 * public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);
 * }
 * 应用示例如：AtomicInteger原子类
 * {@Code
 * public class AtomicInteger extends Number implements java.io.Serializable {
 *     private static final long serialVersionUID = 6214790243416807050L;
 *
 *     // setup to use Unsafe.compareAndSwapInt for updates
 *     private static final Unsafe unsafe = Unsafe.getUnsafe();
 *     private static final long valueOffset;
 *
 *     static {
 *         try {
 *             //调用Unsafe.objectFieldOffset的方法
 *             valueOffset = unsafe.objectFieldOffset
 *                 (AtomicInteger.class.getDeclaredField("value"));
 *         } catch (Exception ex) { throw new Error(ex); }
 *     }
 * }
 * }
 *
 * {@Code 3.线程调度
 * //取消阻塞线程
 * public native void unpark(Object thread);
 *
 * //阻塞线程
 * public native void park(boolean isAbsolute, long time);
 *
 * //获得对象锁（可重入锁）
 * @Deprecated
 * public native void monitorEnter(Object var1);
 *
 * //释放对象锁
 * @Deprecated
 * public native void monitorExit(Object var1);
 *
 * //尝试获取对象锁
 * @Deprecated
 * public native boolean tryMonitorEnter(Object var1);
 * }
 * 应用示例：
 * Java锁和同步器框架的核心类AbstractQueuedSynchronizer，
 * 就是通过调用LockSupport.park()和LockSupport.unpark()实现线程的阻塞和唤醒的，
 * 而LockSupport的park、unpark方法实际上是调用Unsafe的park、unpark方法来实现。
 *
 * {@Code 4.内存屏障
 * //内存屏障，禁止load操作重排序。屏障前的load操作不能被重新排序到屏障后，屏障后
 * //的load操作不能被重新排序到屏障前
 * public native void loadFence();
 *
 * //内存屏障，禁止store操作重排序。屏障前的store操作不能被重新排序到屏障后，屏障
 * //后的store操作不能被重新排序到屏障前
 * public native void storeFence();
 *
 * //内存屏障，禁止load、store操作重排序
 * public native void fullFence();
 * }
 * 应用示例：
 * 在Java 8中引入了一种锁的新机制——{@link StampedLock}，它可以看成是读写锁的一个改进版本。
 * StampedLock提供了一种乐观读锁的实现，这种乐观读锁类似于无锁的操作，完全不会阻塞
 * 写进程获取写锁，从而缓解读多写少时写线程“饥饿”现象。由于StampedLock提供的乐观读
 * 锁不阻塞写线程获取读锁，当线程共享变量从主内存load到线程工作内存时，会存在数据不
 * 一致问题，所以当使用StampedLock的乐观读锁时，需要遵从如下图用例中使用的模式来确
 * 保数据的一致性
 *
 * @author study
 * @version : UnsafeInstance.java, v 0.1 2020年08月11日 0:30 study Exp $
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}