/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.hash;

/**
 * BitMap：位图，
 *
 * 前置基础知识：
 * 1Byte = 8bit
 * int = 4byte即32bit
 * float = 4byte即32bit
 * long=8byte即64bit
 * char=2byte即16bit
 *
 * 移位运算：
 * 左移 << ： 8 << 2 = > 8*4=32
 * 8:     0000 0000 0000 0000 0000 0000 0000 1000
 * <<2: 0000 0000 0000 0000 0000 0000 0010 0000    => 2^5= 2*2*2*2*2=32
 * 右移 >>:8 >> : 8 / 4 = 2
 * 8:     0000 0000 0000 0000 0000 0000 0000 1000
 * <<2: 0000 0000 0000 0000 0000 0000 0000 0010      => 2^1=2
 *  8 / 4 => 8 >> 2
 * 8*4 => 8 << 2
 * 位于&:同位上的两个数都是1，则位是1，否则为0
 * 位或|:同位上的两个数只要有一个为1，则位是1，否则为0
 * 位异^: 同位上的两个数只要不相同时，则位是1，否则为0
 *
 * 问题：假设我们有N{2,3,64,...}个数字，每个数字的范围是0~2亿，给你一台内存
 * 为500M的机器，如何判断一个数是否存在？
 *
 * 假设我们有N{2，3，64}个数中最大的是MAX，那么我们只需要开int[MAX /32+1]个
 * int数组就可以存储完这些数据，因为 1 int = 4 byte = 4 * 8bit =32 bit，假设
 * Max = 66,int[Max/32+1]=int[66/32+1]=int[3]，即int数组长度为3即可
 * 初始化时，如下
 * Data[0]:0~31 32位：0000 0000 0000 0000 0000 0000 0000 0000
 * Data[1]:32~63 32位：0000 0000 0000 0000 0000 0000 0000 0000
 * Data[2]:64~95 32位：0000 0000 0000 0000 0000 0000 0000 0000
 *
 * 对于数字2，2/32 = 0，则2在data[0]，2%32=2，则在data[0]的下标为2的位置
 * （即第3个位置，下标从0开始），将该位置置为1
 * 将data[0]对应bit置为1，如Data[0]:0~31 32位：0000 0000 0000 0000 0000 0000 0000 0100
 *
 * 对于数字3，3/32 = 0，则3在data[0]，3%32=3，则在data[0]的下标为3的位置
 * （即第4个位置，下标从0开始），将该位置置为1
 * 将data[0]对应bit置为1，如Data[0]:0~31 32位：0000 0000 0000 0000 0000 0000 0000 1100
 *
 * 对于数字64，64/32=2，则64在data[3],64%32=0，则在data[3]的下标为0的位置
 * （即第1个位置，下标从0开始），将该位置置为1
 * 将data[0]对应bit置为1，如Data[2]:64~95 32位：0000 0000 0000 0000 0000 0000 0000 0001
 *
 *
 * @author study
 * @version : BitMapByByte.java, v 0.1 2020年07月18日 14:23 study Exp $
 */
public class BitMapByByte {
    //如果是byte，那就是一个只能存8个数
    // 如果是int，则一个能存32个数
    byte[] bits;
    //表示最大的那个数
    int max;

    public BitMapByByte(int max) {
        this.max = max;
        //即 max/8+1
        bits = new byte[(max>>3)+1];
    }

    /**
     * 插入
     * */
    public void add(int n){
        // 不能添加比最大值大的数值
        if(n > max){
            return;
        }
        // 除以8 就可以知道在那个byte
        int index = n >> 3;
        //这里其实还可以用&运算
        //%运算转为 &运算的 前提 是取余的数为2^n幂，n & (2^n-1) == n % 2^n
        //int loc = n % 8;
        int loc = n & 7;
        //接下来就是要把bit数组里面的 bisIndex这个下标的byte里面的 第loc 个bit位置为1
        //位或|：同位上的两个数只要有一个为1，则位是1，否则为0
        bits[index] |= 1 << loc;
    }
    /**
     * 查找
     * */
    public boolean find(int n){
        // 除以8 就可以知道在那个byte
        int index = n >> 3;
        //这里其实还可以用&运算
        //int loc = n % 8;
        int loc = n & 7;
        //如果原来的那个位置是0 那肯定就是0 只有那个位置是1 才行
        //位于&：同位上的两个数都是1，则位是1，否则为0
        int flag = bits[index] & (1 << loc);
        if(flag == 0){
            return false;
        }
        return true;
    }

    /**
     * 删除
     * */
    public boolean remove(int n){
        // 除以8 就可以知道在那个byte
        int index = n >> 3;
        //这里其实还可以用&运算
        //int loc = n % 8;
        int loc = n & 7;
        //要删除的数字存在时，才能进行删除
        if(find(n)){
            //位异^: 同位上的两个数只要不相同时，则位是1，否则为0
            bits[index] ^= 1 << loc;
            return true;
        }
        return false;
    }

    /**
     * 25000001
     * true
     * false
     * true
     * true
     * false
     * */
    public static void main(String[] args) {
        BitMapByByte bitMapWithByte = new BitMapByByte(200000001);  //10亿
        System.out.println(bitMapWithByte.bits.length);

        bitMapWithByte.add(2);
        bitMapWithByte.add(3);
        bitMapWithByte.add(65);
        bitMapWithByte.add(66);

        System.out.println(bitMapWithByte.find(3));
        System.out.println(bitMapWithByte.find(64));
        System.out.println(bitMapWithByte.find(65));
        System.out.println(bitMapWithByte.remove(65));
        System.out.println(bitMapWithByte.find(65));
    }
}