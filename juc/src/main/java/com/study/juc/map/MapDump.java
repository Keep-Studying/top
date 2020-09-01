/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.map;

import java.util.HashMap;

/**
 * @author study
 * @version : MapDump.java, v 0.1 2020年08月27日 21:23 study Exp $
 */
public class MapDump {
    public static void main(String[] args) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>(5);
    }

    //jdk8 高低位扩容
    /*private final void transfer(Node<K,V>[] tab, Node<K,V>[] nextTab) {
        int n = tab.length, stride;
        if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
            // subdivide range，每个线程最少迁移16个槽位，大的话，最多
            stride = MIN_TRANSFER_STRIDE;
        // initiating 才开始初始化新的nextTab
        if (nextTab == null) {
            try {
                @SuppressWarnings("unchecked")
                Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n << 1]; //扩容2倍
                nextTab = nt;
            } catch (Throwable ex) { // try to cope with OOME
                sizeCtl = Integer.MAX_VALUE;
                return;
            }
            nextTable = nextTab;
            transferIndex = n;//更新的转移下标，
        }
        int nextn = nextTab.length;
        ForwardingNode<K,V> fwd = new ForwardingNode<K,V>(nextTab);
        //是否能够向前推进到下一个周期
        boolean advance = true;
        // to ensure sweep before committing nextTab，完成状态，如果是，则结束此方法
        boolean finishing = false;
        for (int i = 0, bound = 0;;) {
            Node<K,V> f; int fh;
            while (advance) { //取下一个周期
                int nextIndex, nextBound;
                //本线程处理的区间范围为[bound, i),范围还没有处理完成，那么就继续处理
                if (--i >= bound || finishing)
                    advance = false;
                    //目前处理到了这里（从大到小， 下线），开始找新的一轮的区间
                else if ((nextIndex = transferIndex) <= 0) {
                    i = -1;
                    advance = false;
                }
                //这个条件改变的是transferIndex的值，从16变成了1
                else if (U.compareAndSwapInt
                        (this, TRANSFERINDEX, nextIndex,
                                //nextBound 是这次迁移任务的边界，注意，是从后往前
                                nextBound = (nextIndex > stride ?
                                        nextIndex - stride : 0))) {
                    bound = nextBound; //一块区间最小桶的下标
                    i = nextIndex - 1; //能够处理的最大桶的下标
                    advance = false;
                }
            }
            if (i < 0 || i >= n || i + n >= nextn) { //每个迁移线程都能达到这里
                int sc;
                if (finishing) { //迁移完成
                    nextTable = null;
                    //直接把以前的table丢弃了，上面的MOVE等标志全部丢弃，使用新的
                    table = nextTab;
                    sizeCtl = (n << 1) - (n >>> 1); //扩大2n-0.5n = 1.50n, 更新新的容量阈值
                    return;
                }
                //表示当前线程迁移完成了
                if (U.compareAndSwapInt(this, SIZECTL, sc = sizeCtl, sc - 1)) {
                    //注意此时sc的值并不等于sizeCtl，上一步，sizeCtl=sizeCtl-1了。这两个对象还是分割的
                    if ((sc - 2) != resizeStamp(n) << RESIZE_STAMP_SHIFT)
                        return;
                    finishing = advance = true;
                    i = n; // recheck before commit
                }
            }
            //如果对应位置为null， 则将ForwardingNode放在对应的地方
            else if ((f = tabAt(tab, i)) == null)
                advance = casTabAt(tab, i, null, fwd);
            else if ((fh = f.hash) == MOVED) //别的线程已经在处理了，再推进一个下标
                advance = true; // already processed，推动到下一个周期，仍然会检查i与bound是否结束
            else { //说明位置上有值了，
                //需要加锁，防止再向里面放值，在放数据时，也会锁住。比如整个table正在迁移，还没有迁移到这个元素，另外一个线程像这个节点插入数据，此时迁移到这里了，会被阻塞住
                synchronized (f) {
                    if (tabAt(tab, i) == f) {//判断i下标和f是否相同
                        Node<K,V> ln, hn; //高位桶， 地位桶
                        if (fh >= 0) {
                            int runBit = fh & n;//n为2^n, 取余后只能是2^n
                            Node<K,V> lastRun = f;
                            ///找到最后一个不和fn相同的节点
                            for (Node<K,V> p = f.next; p != null; p = p.next) {
                                int b = p.hash & n;
                                //只要找到这，之后的取值都是一样的，下次循环时，就不用再循环后面的
                                if (b != runBit) {
                                    runBit = b;
                                    lastRun = p;
                                }
                            }
                            if (runBit == 0) {
                                ln = lastRun;
                                hn = null;
                            }
                            else { //比如1，16，32,如果低位%16，那么肯定是0。
                                hn = lastRun;
                                ln = null;
                            }
                            for (Node<K,V> p = f; p != lastRun; p = p.next) {
                                int ph = p.hash; K pk = p.key; V pv = p.val;
                                if ((ph & n) == 0)
                                    //这样就把相同串的给串起来了
                                    ln = new Node<K,V>(ph, pk, pv, ln);
                                else
                                    //这样就把相同串的给串起来了，注意这里ln用法，第一个next为null，烦着串起来了。
                                    hn = new Node<K,V>(ph, pk, pv, hn);
                            }
                            setTabAt(nextTab, i, ln); //反着给串起来了
                            setTabAt(nextTab, i + n, hn);
                            setTabAt(tab, i, fwd);
                            advance = true;
                        }
                        else if (f instanceof TreeBin) {// 如果是红黑树
                            TreeBin<K,V> t = (TreeBin<K,V>)f;
                            TreeNode<K,V> lo = null, loTail = null; //也是高低节点
                            TreeNode<K,V> hi = null, hiTail = null;//也是高低节点
                            int lc = 0, hc = 0;
                            for (Node<K,V> e = t.first; e != null; e = e.next) { //中序遍历红黑树
                                int h = e.hash;
                                TreeNode<K,V> p = new TreeNode<K,V>
                                        (h, e.key, e.val, null, null);
                                if ((h & n) == 0) { //0的放低位
                                    //注意这里p.prev = loTail，每一个p都是下一个的prev
                                    if ((p.prev = loTail) == null)
                                        lo = p; //把头记住
                                    else
                                        loTail.next = p; //上一次的p的next是这次的p
                                    loTail = p; //把上次p给记住
                                    ++lc;
                                }
                                else { //高位
                                    if ((p.prev = hiTail) == null)
                                        hi = p; //把尾记住
                                    else
                                        hiTail.next = p;
                                    hiTail = p;
                                    ++hc;
                                }
                            }
                            ln = (lc <= UNTREEIFY_THRESHOLD) ? untreeify(lo) :// //判断是否需要转化为树
                                    (hc != 0) ? new TreeBin<K,V>(lo) : t; //如果没有高低的话，则部分为两个树
                            hn = (hc <= UNTREEIFY_THRESHOLD) ? untreeify(hi) :
                                    (lc != 0) ? new TreeBin<K,V>(hi) : t;
                            setTabAt(nextTab, i, ln);
                            setTabAt(nextTab, i + n, hn);
                            setTabAt(tab, i, fwd);
                            advance = true;
                        }
                    }
                }
            }
        }
    }*/
}