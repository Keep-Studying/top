/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.unsafe;

import com.study.juc.utils.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * Unsafe内存操作应用示例
 * {@link Unsafe#allocateMemory(long)}
 * {@link Unsafe#putAddress(long, long)}
 * {@link Unsafe#getAddress(long)}
 * {@link Unsafe#freeMemory(long)}
 * @author study
 * @version : AllocateMemoryAccess.java, v 0.1 2020年08月24日 21:57 study Exp $
 */
public class AllocateMemoryAccess {

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
        long oneHundred = 1193123491341341234L;
        byte size = 8;
        /*
         * 调用allocateMemory分配内存
         */
        long memoryAddress = unsafe.allocateMemory(size);
        System.out.println("address:->"+memoryAddress);
        /*
         * 将oneHundred写入到内存中
         */
        unsafe.putAddress(memoryAddress, oneHundred);
        /*
         * 内存中读取数据
         */
        long readValue = unsafe.getAddress(memoryAddress);

        System.out.println("value : " + readValue);

        unsafe.freeMemory(memoryAddress);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}