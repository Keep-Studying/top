/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.mesi;

/**
 * 为了避免CPU运算能力的浪费，Store Buffer被引入。处理器把它想要
 * 写入到主存的值写到缓存，然后继续去处理其他事情，当所有失效确认
 * （Invalidate Acknowledge）都接收到时，数据才会最终被提交。
 *
 * 这么做有两个风险：
 * 第一、就是处理器会尝试从存储缓存（Store Buffer）中读取值，但他
 * 还没有进行提交，这个解决方案称为Store Forwarding，它使得加载的
 * 时候，如果存储缓存行中存在，则进行返回。
 * 第二、保存什么时候会完成，这个并没有任何保证。
 * @author study
 * @version : StoreBufferRisks.java, v 0.1 2020年08月11日 0:57 study Exp $
 */
public class StoreBufferRisks {

    private volatile int value = 3;
    private volatile boolean finished;

    void exeToCpuA(){
        value = 10;
        finished = true;
    }
    void exeToCpuB(){
        if(finished){
            //value一定等于10？！
            assert value == 10;
        }
    }

    /**
     * 执行失效也不是一个简单的操作，它需要处理器去处理。另外，存储缓存（Store Buffers）
     * 并不是无穷大的，所以处理器有时需要等待失效确认的返回。这两个操作都会使得性能大幅降低。
     * 为了应付这种情况，引入了失效队列。它们的约定如下：
     * • 对于所有的收到Invalidate的请求，Invalidate Acknowledge消息必须立刻发送
     * • Invalidate并不真正执行，而是被放在一个特殊的队列中，在方便的时候才会去执行
     * • 处理器不会发送任何消息给所处理的缓存条目，直到它处理Invalidate
     *
     * 即便是这样处理器依然不知道什么时候优化是允许的，而什么时候是不允许的。干脆处理器将这个
     * 任务丢给了写代码的人，这就是内存屏障
     * 写屏障 Store Memory Barrier(a.k.a. ST, SMB, smp_wmb)是一条告诉处理器在执行这之后
     * 的指令之前，应用所有已经在存储缓存（store buffer）中的保存的指令。
     *
     * 读屏障Load Memory Barrier (a.k.a. LD, RMB, smp_rmb)是一条告诉处理器在执行任何的加
     * 载前，先应用所有已经在失效队列中的失效操作的指令。
     * */
    void executedOnCpu0() {
        value = 10;
        //在更新数据之前必须将所有存储缓存（store buffer）中的指令执行完毕。
        /*storeMemoryBarrier();*/
        finished = true;
    }
    void executedOnCpu1() {
        while(!finished);
        //在读取之前将所有失效队列中关于该数据的指令执行完毕。
        /*loadMemoryBarrier();*/
        assert value == 10;
    }
}